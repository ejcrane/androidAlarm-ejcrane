package edu.uchicago.ejcrane.androidalarm.data.repos

import edu.uchicago.ejcrane.androidalarm.common.Constants
import edu.uchicago.ejcrane.androidalarm.data.models.YoutubeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class YoutubeRepository (private val youtubeApi: YoutubeApi) {

    suspend fun getVids(
        query: String,
        maxResults: Int,
        pageToken: String?
    ): Response<YoutubeResponse> {

        return withContext(Dispatchers.IO) {
            youtubeApi.getVids(
                part = "snippet",
                query = query,
                maxResults = maxResults,
                type = "video",
                key = Constants.ytApiKey,
                pageToken = pageToken
            )
        }
    }
}