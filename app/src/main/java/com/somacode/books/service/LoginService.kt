package com.somacode.books.service

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import com.somacode.books.controller.HomeActivity
import com.somacode.books.Main
import com.somacode.books.service.retrofit.BooksRetrofit
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers


object LoginService {

    fun postLogin(activity: Activity, username: String, password: String) {

        val response = BooksRetrofit.api.login(username, password)

        response.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
            Main.session.loginResponse = it
            val intent = Intent(activity, HomeActivity::class.java)
            activity.startActivity(intent)
            Toast.makeText(activity, "Login Successful", Toast.LENGTH_SHORT).show()
        }, {
            it.printStackTrace()
            Toast.makeText(activity, "Login Failure", Toast.LENGTH_SHORT).show()
        })

    }

}