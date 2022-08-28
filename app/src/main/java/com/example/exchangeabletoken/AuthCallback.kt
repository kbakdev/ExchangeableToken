package com.example.exchangeabletoken

open class AuthCallback {
    open fun onSuccess(token: String) {}
    open fun onFailure(error: String) {}
}
