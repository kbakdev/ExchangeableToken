package com.example.exchangeabletoken

class Balance {
    var balance: Int = 0
    var id: String? = null
    var name: String? = null
    private var email: String? = null
    private var password: String? = null

    override fun toString(): String {
        return "Balance(balance=$balance, id=$id, name=$name, email=$email, password=$password)"
    }
}
