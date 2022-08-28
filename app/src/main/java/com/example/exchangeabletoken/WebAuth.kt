package com.example.exchangeabletoken

class WebAuth {
    fun getAuthUrl(redirectUrl: String): String {
        return "https://auth.exchangeabletoken.com/auth?redirect_uri=$redirectUrl"
    }

    fun getToken(code: String, redirectUrl: String): String {
        return "https://auth.exchangeabletoken.com/token?code=$code&redirect_uri=$redirectUrl"
    }

    fun getUserInfo(token: String): String {
        return "https://auth.exchangeabletoken.com/userinfo?token=$token"
    }

    fun login(token: String): String {
        return "https://auth.exchangeabletoken.com/login?token=$token"
    }
}
