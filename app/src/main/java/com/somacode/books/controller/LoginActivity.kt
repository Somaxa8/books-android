package com.somacode.books.controller

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import com.somacode.books.R
import com.somacode.books.service.LoginService
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailInput.setText("admin@somacode.com")
        passwordInput.setText("somaxa8")

        loginButton.setOnClickListener {
            login(emailInput.text.toString(), passwordInput.text.toString())
        }
    }

    fun login(username: String, password: String) {
        LoginService.postLogin(this, username, password)
    }
}