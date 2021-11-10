package com.somacode.books.service.retrofit

import com.google.gson.GsonBuilder
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BooksRetrofit {

    val api: BooksApi

    init {
        val gson = GsonBuilder().create()
        val gsonConverter = GsonConverterFactory.create(gson)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api-books.somacode.app")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(gsonConverter).build()
        api = retrofit.create(BooksApi::class.java)
    }

}