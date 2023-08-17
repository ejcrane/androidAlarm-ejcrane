package edu.uchicago.ejcrane.androidalarm.data.repos

import edu.uchicago.ejcrane.androidalarm.common.Constants
import edu.uchicago.ejcrane.androidalarm.data.models.Snippet
import edu.uchicago.ejcrane.androidalarm.data.models.YoutubeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface YoutubeApi {

    @GET(value = "search")
    suspend fun getVids(
        @Query("part") part: String,
        @Query("q") query: String,
        @Query("maxResults") maxResults: Int,
        @Query("pageToken") pageToken: String?,
        @Query("type") type: String,
        @Query("key") key: String
    ): Response<YoutubeResponse>
}