package com.example.exchangeabletoken

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.example.exchangeabletoken.databinding.ActivityAddTransactionBinding
import com.google.firebase.database.Transaction

class AddTransactionActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityAddTransactionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
//        WindowCompat.setDecorFitsSystemWindows(window, false)
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
            val amount = binding.root.findViewById<androidx.appcompat.widget.AppCompatEditText>(R.id.amount).text.toString()
            // validate amount
            if (amount == "") {
                // if amount is empty, or user don't have enough money, show error message
                Snackbar.make(it, "Please enter a valid amount", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
            val receiver = binding.root.findViewById<androidx.appcompat.widget.AppCompatEditText>(R.id.receiver).text.toString()
            if (receiver == "") {
                Snackbar.make(it, "Please enter a receiver", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
                return@setOnClickListener
            }
            val sender = binding.root.findViewById<androidx.appcompat.widget.AppCompatEditText>(R.id.sender).text.toString()
            if (sender == "") {
                Snackbar.make(it, "Please enter a sender", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
                return@setOnClickListener
            }
            val description = binding.root.findViewById<androidx.appcompat.widget.AppCompatEditText>(R.id.description).text.toString()
            if (description == "") {
                Snackbar.make(it, "Please enter a description", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
                return@setOnClickListener
            }

            // timestamp is automatically generated
            val timestamp = java.sql.Timestamp(System.currentTimeMillis())
            val transaction = Transaction(amount, receiver, sender, description, timestamp)
            // check if receiver really exists
            if (!FirebaseDatabase.checkUser(receiver)) {
                Snackbar.make(it, "Receiver does not exist", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
                return@setOnClickListener
            }

            // catch error if transaction is not valid
            try {
                FirebaseDatabase.addTransaction(transaction)
                finish()
            } catch (e: Exception) {
                Snackbar.make(it, "Transaction is not valid", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
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
