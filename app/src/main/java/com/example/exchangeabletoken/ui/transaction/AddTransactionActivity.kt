package com.example.exchangeabletoken.ui.transaction

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.example.exchangeabletoken.R
import com.example.exchangeabletoken.data.model.TransactionBuilder
import com.example.exchangeabletoken.databinding.ActivityAddTransactionBinding
import com.example.exchangeabletoken.utils.ReceiverChecker
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

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

        // do transaction and store it in realtime database, but first check if fields are filled
        binding.fab.setOnClickListener { view ->
            if (validateInput()) {
                doTransaction(view)
            }
        }

    }

    private fun validateInput(): Boolean {
        val amountEditText = binding.root.findViewById<AppCompatEditText>(R.id.amount)
        val receiverEditText = binding.root.findViewById<AppCompatEditText>(R.id.receiver)
        val descriptionEditText = binding.root.findViewById<AppCompatEditText>(R.id.description)

        var isValid = true

        if (amountEditText.text?.isEmpty() == true) {
            amountEditText.error = "Amount is required."
            isValid = false
        }

        if (receiverEditText.text?.isEmpty() == true) {
            receiverEditText.error = "Receiver is required."
            isValid = false
        }

        if (descriptionEditText.text?.isEmpty() == true) {
            descriptionEditText.error = "Description is required."
            isValid = false
        }

        return isValid
    }

    private fun doTransaction(view: View) {
        // get data from edit text
        val amount = binding.root.findViewById<AppCompatEditText>(R.id.amount).text.toString().toDouble()
        val receiver = binding.root.findViewById<AppCompatEditText>(R.id.receiver).text.toString()
        val description = binding.root.findViewById<AppCompatEditText>(R.id.description).text.toString()

        // check if user is providing amount
        if (amount == 0.0) {
            Snackbar.make(view, "Please enter amount", Snackbar.LENGTH_SHORT).show()
            return
        }

        // check if user is providing receiver
        if (receiver.isEmpty()) {
            Snackbar.make(view, "Please enter receiver", Snackbar.LENGTH_SHORT).show()
            return
        }

        // check if receiver exists
        ReceiverChecker().checkReceiver(receiver) { result ->
            if (result) {
                // get receiver uid from database
                val database = Firebase.database
                val receiverRef = database.getReference("users")
                val receiverUidRef = receiverRef.orderByChild("name").equalTo(receiver)

                receiverUidRef.addListenerForSingleValueEvent(object: ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        // get only value of name from snapshot
                        val receiverName = dataSnapshot.children.first().child("name").value.toString()

                        // check if sender has enough funds to complete the transaction
                        val senderName = FirebaseAuth.getInstance().currentUser?.displayName.toString()
                        val senderRef = database.getReference("users/$senderName/balance")
                        val receiverBalanceRef = database.getReference("users/$receiverName/balance")

                        senderRef.addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                val senderBalance = when (dataSnapshot.value) {
                                    is Double -> dataSnapshot.value as Double
                                    is Long -> (dataSnapshot.value as Long).toDouble()
                                    else -> 0.0
                                }
                                if (senderBalance < amount) {
                                    Snackbar.make(view, "Insufficient funds", Snackbar.LENGTH_SHORT).show()
                                    return
                                }

                                // subtract amount from sender balance
                                senderRef.setValue(senderBalance - amount)

                                // add amount to receiver balance
                                receiverBalanceRef.addListenerForSingleValueEvent(object : ValueEventListener {
                                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                                        val receiverBalance = when (dataSnapshot.value) {
                                            is Double -> dataSnapshot.value as Double
                                            is Long -> (dataSnapshot.value as Long).toDouble()
                                            else -> 0.0
                                        }
                                        receiverBalanceRef.setValue(receiverBalance + amount)
                                    }

                                    override fun onCancelled(error: DatabaseError) {
                                        // Failed to read value
                                        Log.w("Error", "Failed to read value.", error.toException())
                                    }
                                })

                                // create a new transaction using TransactionBuilder
                                val transaction = TransactionBuilder()
                                    .setAmount(amount)
                                    .setReceiver(receiver)
                                    .setDescription(description)
                                    .build()

                                // create a new transaction in realtime database based on information provided by user
                                val myRef = database.getReference("transactions")
                                val unixTime = System.currentTimeMillis() / 1000L
                                myRef.child(unixTime.toString()).setValue(transaction)

                                // change scene to TransactionActivity
                                finish()
                            }

                            override fun onCancelled(error: DatabaseError) {
                                // Failed to read value
                                Log.w("Error", "Failed to read value.", error.toException())
                            }
                        })
                    }

                    override fun onCancelled(error: DatabaseError) {
                        // Failed to read value
                        Log.w("Error", "Failed to read value.", error.toException())
                    }
                })
            } else {
                Snackbar.make(view, "User doesn't exist", Snackbar.LENGTH_SHORT).show()
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
