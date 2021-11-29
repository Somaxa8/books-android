package com.somacode.books.service

import android.app.Activity
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.somacode.books.adapter.BookAdapter
import com.somacode.books.controller.BookActivity
import com.somacode.books.controller.HomeActivity
import com.somacode.books.model.Book
import com.somacode.books.service.retrofit.BooksRetrofit
import com.somacode.books.ui.home.HomeFragment
import com.somacode.books.ui.home.HomeViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit

object BookService {

    fun getBooks(bookAdapter: BookAdapter, books: MutableList<Book>, page: Int, size: Int, search: String, callback: () -> Unit) {
        val response = BooksRetrofit.api.getBooks(page, size, search)

        response.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ res ->
            res.forEach {
                books.add(it)
                bookAdapter.notifyItemChanged(books.lastIndex)
            }

            callback()
        }, {
            it.printStackTrace()
        })
    }

    fun getBook(activity: BookActivity, id: Long, callback: () -> Unit) {
        val response = BooksRetrofit.api.getBook(id)

        response.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ res ->
            activity.bookViewModel.updateBook(res)
            callback()
        }, {
            it.printStackTrace()
        })
    }

}