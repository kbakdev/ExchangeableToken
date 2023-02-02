package com.example.exchangeabletoken

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.example.exchangeabletoken.databinding.ActivityAddTransactionBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.sql.Timestamp

class AddTransactionActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityAddTransactionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
<<<<<<< HEAD
=======
//        WindowCompat.setDecorFitsSystemWindows(window, false)
>>>>>>> d6b29e1 (feat: app minor upgrade)
        super.onCreate(savedInstanceState)

        binding = ActivityAddTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        // change scene to TransactionActivity
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

<<<<<<< HEAD
        // do transaction and store it in realtime database
<<<<<<< HEAD
        binding.fab.setOnClickListener { view ->
            doTransaction(view)
        }
    }
=======
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
>>>>>>> d6b29e1 (feat: app minor upgrade)

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
                        // get only value of uid from snapshot
                        val receiverUid = dataSnapshot.children.first().key.toString()

                        // check if sender has enough funds to complete the transaction
                        val senderUid = FirebaseAuth.getInstance().currentUser!!.uid
                        val senderRef = database.getReference("users/$senderUid/balance")
                        val receiverBalanceRef = database.getReference("users/$receiverUid/balance")

                        senderRef.addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                val senderBalance = dataSnapshot.getValue(Double::class.java)!!
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
=======
        // do transaction
        binding.fab.setOnClickListener {
            finish()
>>>>>>> 47c1ddf (fix: CI workflow)
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
