package com.somacode.books.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.somacode.books.Main

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Main.session.loginResponse?.token?.accessToken?.let {
            startActivity(Intent(this, HomeActivity::class.java))
        } ?:run {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}