package com.example.exchangeabletoken

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.example.exchangeabletoken.databinding.ActivityAddTransactionBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import java.sql.Timestamp

class AddTransactionActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityAddTransactionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        // change scene to TransactionActivity
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        // do transaction and store it in realtime database
        binding.fab.setOnClickListener {
            // get data from content_add_transaction.xml which is in activity_add_transaction.xml
            val amount = binding.root.findViewById<AppCompatEditText>(R.id.amount).text.toString()
            // validate amount
            // if amount is empty, or user don't have enough money, show error message
            val receiver = binding.root.findViewById<AppCompatEditText>(R.id.receiver).text.toString()
            if (amount == "") {
                Snackbar.make(it, "Please enter an amount", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
                // then stop the function
                return@setOnClickListener
            }
            val user = FirebaseAuth.getInstance().currentUser
            if (user != null) {
                val uid = user.uid
                val balance = FirebaseDatabase.getBalance(uid)
                if (balance == "") {
                    Snackbar.make(it, "You don't have enough money", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                    return@setOnClickListener
                }
                // check if balance is 0
                if (balance == "0") {
                    Snackbar.make(it, "You don't have enough money", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                    return@setOnClickListener
                }
            }

            if (receiver == "") {
                Snackbar.make(it, "Please enter a receiver", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
                return@setOnClickListener
            } else if (!FirebaseDatabase.checkUser(receiver)) {
                Snackbar.make(it, "Receiver does not exist", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
                return@setOnClickListener
            }
            val description = binding.root.findViewById<AppCompatEditText>(R.id.description).text.toString()
            if (description == "") {
                Snackbar.make(it, "Please enter a description", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
                return@setOnClickListener
            }

            // timestamp is automatically generated
            val timestamp = Timestamp(System.currentTimeMillis())
            // set sender as current user
            val sender = FirebaseAuth.getInstance().currentUser?.email.toString()

            val transaction = Transaction(sender, receiver, amount.toInt(), description, timestamp)
            // check if receiver really exists
            if (!FirebaseDatabase.checkUser(receiver)) {
                Snackbar.make(it, "Receiver does not exist", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
                return@setOnClickListener
            }

            // catch error if transaction is not valid
            try {
                FirebaseDatabase.addTransaction(transaction, receiver)
                Snackbar.make(it, "Transaction is successful", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            } catch (e: Exception) {
                Snackbar.make(it, "Transaction is not valid", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
                return@setOnClickListener
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_add_transaction)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}

private operator fun Any.not(): Boolean {
    return false
}
