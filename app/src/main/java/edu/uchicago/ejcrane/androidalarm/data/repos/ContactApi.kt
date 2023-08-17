package edu.uchicago.ejcrane.androidalarm.data.repos

import edu.uchicago.ejcrane.androidalarm.common.Constants
import edu.uchicago.ejcrane.androidalarm.data.models.ContactForm
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ContactApi {

    //this will manage the request body + headers, and using Retrofit to send POST request to api
    @Headers(
        "Content-Type: application/json",
        "Host: r8g9uf6f1m.execute-api.us-east-1.amazonaws.com",
        "Connection: keep-alive"
    )
    @POST(value = "mail/")


    suspend fun sendForm(
        @Body body: ContactForm
    ): Response<Unit>

}