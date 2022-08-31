package com.example.exchangeabletoken

import android.accounts.Account
import android.graphics.Color
import android.net.Credentials
import android.os.Bundle
import android.view.View
import android.view.View.inflate
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.core.content.res.ComplexColorCompat.inflate
import androidx.core.graphics.drawable.DrawableCompat.inflate
import androidx.core.view.isVisible
import com.example.exchangeabletoken.R.string.login_success_message
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {
//    // Access to onscreen controls
//    private lateinit var binding: ActivityLoginBinding
//
//    // App and user Status
//    private lateinit var account: Account
//    private var isLoggedIn = false
//    private var appJustLaunched = true
//    private var userIsAuthenticated = false
//
//    // Account data
//    private var user = User()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        // TODO: implement this
//        binding = ActivityLoginBinding.inflate(layoutInflater)
        // TODO: implement this
        // setContentView(binding.root)

//        account = Account("exchangeabletoken", "com.example.exchangeabletoken")
        // TODO: implement this
        //             getString(R.string.com_auth0_client_id),
        //            getString(R.string.com_auth0_domain)

        // TODO: implement this
        //        binding.loginButton.setOnClickListener { login() }
        //        binding.logoutButton.setOnClickListener { logout() }

    }
}

//    private fun login() {
        // TODO: implement this
//        WebAuthProvider.login(account).withScheme("Bearer").start(this, object : AuthCallback {
//            override fun onFailure(error: String) {
//                showSnackBar(getString(R.string.login_failed))
//            }
//
//            override fun onSuccess(token: String) {
//                userIsAuthenticated = true
////                TODO: implement this
////                 val idToken = credentials.idToken
////                 user = User(idToken)
////                 showSnackBar(getString(login_success_message, user.name))
//                isLoggedIn = true
//                userIsAuthenticated = true
//                updateUI()
//            }
//        })
//    }
//
//
//    private fun logout() {
//        WebAuthProvider.logout(account).withScheme(getString(R.string.com_auth_scheme)).start(this, object : AuthCallback {
//            override fun onFailure(exception: AuthenticationException) {
//                // for some reason, logout failed
//                showSnackBar(getString(R.string.general_failure_with_exception_code, exception.getCode()))
//            }
//            override fun onSuccess(payload: Void?) {
//                // The user successfully logged out
//                user = User()
//                userIsAuthenticated = false
//                isLoggedIn = false
//                updateUI()
//            }
//        })
//    }
//    private fun updateUI() {
//        if (appJustLaunched) {
//            appJustLaunched = false
//            binding.textviewTitle.text = getString(R.string.initial_title)
//            appJustLaunched = false
//        } else {
//            if (isLoggedIn) {
//                binding.textviewTitle.text = getString(R.string.logged_in_title)
//            } else {
//                binding.textviewTitle.text = getString(R.string.logged_out_title)
//            }
//        }
//
//    binding.buttonLogin.visibility = if (isLoggedIn) View.GONE else View.VISIBLE
//    binding.buttonLogout.visibility = if (isLoggedIn) View.VISIBLE else View.GONE
//
//    binding.buttonLogin.setOnClickListener { login() }
//    binding.buttonLogout.setOnClickListener { logout() }
//
//    binding.buttonLogin.isEnabled = !isLoggedIn
//    binding.buttonLogout.isEnabled = isLoggedIn
//
//    binding.buttonLogin.setTextColor(if (isLoggedIn) Color.GRAY else Color.BLACK)
//    binding.buttonLogout.setTextColor(if (isLoggedIn) Color.BLACK else Color.GRAY)
//
//    binding.buttonLogin.isEnabled = !userIsAuthenticated
//    binding.buttonLogout.isEnabled = userIsAuthenticated
//
//    binding.textviewUserProfile.isVisible = userIsAuthenticated
//    binding.textviewUserProfile.text = getString(R.string.user_profile_message, user.name, user.email)
//
//    binding.textviewTitle.isVisible = !userIsAuthenticated
//
//    binding.imageviewUser.isVisible = userIsAuthenticated
//    binding.imageviewUser.load(user.picture)
//    }

    // Utility functions
    // =================

    /**
     * This is a convenience method that simplifies the process
     * of displaying a SnackBar.
     *
     * @param text The text that the SnackBar should display.
     */

//    private fun showSnackBar(text: String) {
//        Snackbar.make(binding.root, text, Snackbar.LENGTH_SHORT).show()
//    }
//}
