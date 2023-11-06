package com.example.davaleba_5.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.davaleba_5.R
import com.example.davaleba_5.databinding.ActivityLogoutBinding
import com.example.davaleba_5.databinding.ActivityRegister2Binding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LogoutActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLogoutBinding
    private lateinit var auth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLogoutBinding.inflate(layoutInflater)
        auth = Firebase.auth

        setContentView(binding.root)

        val username = intent.getStringExtra("username")
        val greetingTextView = binding.textViewGreeting

        greetingTextView.text = "Hello $username"

        if (username == null)
            greetingTextView.text = "Welcome Back"

    }
}