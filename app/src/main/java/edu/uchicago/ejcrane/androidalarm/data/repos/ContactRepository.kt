package edu.uchicago.ejcrane.androidalarm.data.repos

import edu.uchicago.ejcrane.androidalarm.data.models.ContactForm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ContactRepository (private val contactApi: ContactApi) {
    val contactForm = ContactForm()

    suspend fun sendForm(
        email: String,
        subject: String,
        body: String
    ): Response<Unit> {
        contactForm.email = email
        contactForm.subject = subject
        contactForm.body = body
        return withContext(Dispatchers.IO) {contactApi.sendForm(contactForm)}
    }
}