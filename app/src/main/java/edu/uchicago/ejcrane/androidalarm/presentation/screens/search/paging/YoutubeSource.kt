package edu.uchicago.ejcrane.androidalarm.presentation.screens.search.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import edu.uchicago.ejcrane.androidalarm.data.models.Item
import edu.uchicago.ejcrane.androidalarm.data.repos.YoutubeRepository

class YoutubeSource (
    private val youtubeRepository: YoutubeRepository,
    private val paginateData: Paginate
) : PagingSource<Int, Item>() {
    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        return try {
            val prev = params.key ?: 0

            val response = youtubeRepository.getVids(
                maxResults = params.loadSize,
                query = paginateData.query,
                pageToken = paginateData.pageToken
            )

            if (response.isSuccessful) {
                val body = response.body()?.items
                LoadResult.Page(
                    data = body!!,
                    prevKey = if (prev == 0) null else prev - 1,
                    nextKey = if (body.size < params.loadSize) null else prev + 10
                )
            } else {
                LoadResult.Error(Exception(response.message()))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override val keyReuseSupported: Boolean
        get() = true
}