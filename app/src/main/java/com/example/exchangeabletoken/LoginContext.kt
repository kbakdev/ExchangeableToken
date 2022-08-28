package com.example.exchangeabletoken

import javax.security.auth.Subject
import javax.security.auth.callback.CallbackHandler

class LoginContext(s: String, callbackHandler: CallbackHandler, nothing: Nothing?, options: Map<String, *>) {
    val subject: Subject = Subject()

    fun login() {
        println("login")
    }
}
