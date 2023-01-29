package com.example.exchangeabletoken

class Balance {
    var balance: Int = 0
    var id: String? = null
    var name: String? = null
    private var email: String? = null
    private var password: String? = null

    constructor() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    constructor(balance: Int, id: String?, name: String?, email: String?, password: String?) {
        this.balance = balance
        this.id = id
        this.name = name
        this.email = email
        this.password = password
    }

    override fun toString(): String {
        return "Balance(balance=$balance, id=$id, name=$name, email=$email, password=$password)"
    }
}
