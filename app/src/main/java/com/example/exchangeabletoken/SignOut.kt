package com.example.exchangeabletoken

import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignOut {
    companion object {
        fun signOut() {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            Firebase.auth.signOut()
        }

        private fun startActivity(intent: Intent) {
            startActivity(intent)
        }
    }
}