package com.example.exchangeabletoken

data class User(val idToken: String? = null) {

    var id = ""
    private var name = ""
    private var email = ""
    private var emailVerified = ""
    var picture = ""
    private var updatedAt = ""

    init {
        try {
            // Attempt to decode the ID token.
            val jwt = JWT(idToken ?: "")

            // The ID token is a valid JWT,
            // so extract information about the user from it.
            id = jwt.subject ?: ""
            name = jwt.getClaim("name").toString() ?: ""
            email = jwt.getClaim("email").toString() ?: ""
            emailVerified = jwt.getClaim("email_verified").toString() ?: ""
            picture = jwt.getClaim("picture").toString() ?: ""
            updatedAt = jwt.getClaim("updated_at").toString() ?: ""

        } catch (e: com.example.exchangeabletoken.Throwable) {
            // The ID token is NOT a valid JWT,
            // so leave the user properties as empty strings.
        }
    }

}