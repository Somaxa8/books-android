package com.somacode.books.controller.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.somacode.books.model.Book

class BookViewModel: ViewModel() {
    val book = MutableLiveData<Book>()

    fun updateBook(value: Book) {
        book.postValue(value)
    }
}