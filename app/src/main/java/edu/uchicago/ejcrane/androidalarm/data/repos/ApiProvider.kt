package edu.uchicago.ejcrane.androidalarm.data.repos

import com.google.gson.Gson
import edu.uchicago.ejcrane.androidalarm.common.Constants
import retrofit2.Retrofit
import edu.uchicago.ejcrane.androidalarm.data.repos.YoutubeApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiProvider {

    fun youtubeApi(): YoutubeApi {
        return Retrofit.Builder()
            .baseUrl(Constants.ytBaseUrl)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(YoutubeApi::class.java)
    }

    private fun getOkHttpClient() = OkHttpClient.Builder().addInterceptor(getLoggingInterceptor()).build()

    private fun getLoggingInterceptor() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
}