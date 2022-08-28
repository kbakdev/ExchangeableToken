package com.example.exchangeabletoken

import java.security.AuthProvider
import javax.security.auth.Subject
import javax.security.auth.callback.CallbackHandler
import javax.security.auth.login.LoginException

class WebAuthProvider : AuthProvider("WebAuthProvider", 0.1, "WebAuthProvider") {
    fun getSubject(subject: Subject, callbackHandler: CallbackHandler, sharedState: Map<String, *>, options: Map<String, *>): Subject {
        val loginContext = LoginContext("WebAuth", callbackHandler, null, options)
        try {
            loginContext.login()
        } catch (e: LoginException) {
            throw RuntimeException(e)
        }
        return loginContext.subject
    }

    override fun login(subject: Subject?, handler: CallbackHandler?) {
        TODO("Not yet implemented")
    }

    override fun logout() {
        TODO("Not yet implemented")
    }

    override fun setCallbackHandler(handler: CallbackHandler?) {
        TODO("Not yet implemented")
    }
}