package edu.uchicago.ejcrane.androidalarm.presentation.viewmodels

import edu.uchicago.ejcrane.androidalarm.data.repos.ContactRepository
import edu.uchicago.ejcrane.androidalarm.data.repos.ContactBuilder
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class ContactViewModel: ViewModel() {

    private val contactRepository: ContactRepository = ContactRepository(ContactBuilder.contactApi())

    //MUTABLE STATES
    private var _email = mutableStateOf("")
    val email: State<String> = _email

    private var _subject = mutableStateOf("")
    val subject: State<String> = _subject

    private var _body = mutableStateOf("")
    val body: State<String> = _body

    //FUNCTIONS
    fun setEmail(email: String) {
        _email.value = email
    }

    fun setSubject(subject: String) {
        _subject.value = subject
    }

    fun setBody(body: String) {
        _body.value = body
    }

    fun onSend() {
        println("function starting")
        viewModelScope.launch {
            val response = contactRepository.sendForm(
                email = email.value,
                subject = subject.value,
                body = body.value
            )

            if(!response.isSuccessful) {
                println(response.headers())
            }
        }

    }
}