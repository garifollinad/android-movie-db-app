package com.example.movie_db

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in_activity)

        val signUpText = findViewById<TextView>(R.id.tv_link_sign_up)
        val sharedPreferences = getSharedPreferences("UserInfo", 0)
        val login = findViewById<EditText>(R.id.et_username)
        val password = findViewById<EditText>(R.id.et_password)
        val loginBtn = findViewById<Button>(R.id.login_btn)

        signUpText.setOnClickListener {
            val toRegister = Intent(applicationContext, SignUpActivity::class.java)
            startActivity(toRegister)
        }

        loginBtn.setOnClickListener {
            val loginVal = login.text.toString()
            val passwordVal = password.text.toString()
            val signedUpLogin = sharedPreferences.getString("username", "")
            val signedUpPassword = sharedPreferences.getString("password", "")

            if (loginVal == signedUpLogin &&
                passwordVal == signedUpPassword
            ) {
                Toast.makeText(applicationContext, "Sign in completed", Toast.LENGTH_SHORT).show()
                val signInFinish = Intent(applicationContext, FragmentsActivity::class.java)
                startActivity(signInFinish)
            } else {
                Toast.makeText(
                    applicationContext,
                    "Failed to sign in. Invalid username or password",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}