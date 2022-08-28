package com.example.exchangeabletoken

import org.json.JSONObject
import java.util.*

class JWT(s: String) {
    private val token = s
    private val it = JSONObject(s)
    private val header = it.getJSONObject("header")

    val subject: String?
        get() {
            return header.getString("sub")
        }


    fun getClaim(key: String): String? {
        val parts = token.split(".")
        val payload = parts[1]
        val json = String(Base64.getDecoder().decode(payload))
        val jsonObject = JSONObject(json)
        return jsonObject.getString(key)
    }
}
