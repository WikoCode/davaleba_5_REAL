package com.example.davaleba_5.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.widget.Toast
import com.example.davaleba_5.R
import com.example.davaleba_5.databinding.ActivityRegister2Binding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore  // Import Firestore

class Register2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityRegister2Binding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegister2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val usernameEditText = binding.etUsername
        val signUp = binding.btnSignUp

        val db = FirebaseFirestore.getInstance()




            val username = usernameEditText.text.toString()


            val user = Firebase.auth.currentUser
            val userId = user?.uid

            if (userId != null) {
                val userDocumentRef = db.collection("users").document(userId)

                val userData = hashMapOf(
                    "username" to username
                )
                userDocumentRef.set(userData)
                    .addOnSuccessListener {
                        val intent = Intent(this, LogoutActivity::class.java)
                        intent.putExtra("username", username)
                        startActivity(intent)
                        finish()


                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show()
                    }
            }

        signUp.setOnClickListener {
            val username = usernameEditText.text.toString()

            val intent2 = Intent(this, LogoutActivity::class.java)
            intent2.putExtra("username", username)
            startActivity(intent2)
            finish()
        }








    }
}
