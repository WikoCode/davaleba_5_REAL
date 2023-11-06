package com.example.davaleba_5.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.davaleba_5.R
import com.example.davaleba_5.databinding.ActivityMainBinding
import com.example.davaleba_5.databinding.ActivityRegisterBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        auth = Firebase.auth

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val emailRegister = binding.etEmailRegister
        val passwordRegister = binding.etPasswordRegister
        val nextButton = binding.btnNext


        nextButton.setOnClickListener {
            val email = emailRegister.text.toString().trim()
            val password = passwordRegister.text.toString().trim()


            if (isValidEmail(email) && isValidPassword(password)) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                            val intent = Intent(this, Register2Activity::class.java)
                            startActivity(intent)
                            finish()

                        } else {

                            val exception = task.exception
                        }
                    }
            } else {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()

            }




        }



    }

    private fun isValidPassword(password: Any): Boolean {
        return password.toString().length >= 8
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()

    }
}