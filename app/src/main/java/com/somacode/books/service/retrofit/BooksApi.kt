package com.somacode.books.service.retrofit

import com.somacode.books.model.Book
import com.somacode.books.model.response.LoginResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface BooksApi {

    // OAuth
    @POST("/public/oauth/login")
    @FormUrlEncoded
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Single<LoginResponse>

    // Books
    @GET("/public/books")
    fun getBooks(
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("search") search: String
    ): Single<MutableList<Book>>

    @GET("/public/books/{id}")
    fun getBook(
        @Path("id") id: Long
    ): Single<Book>

}