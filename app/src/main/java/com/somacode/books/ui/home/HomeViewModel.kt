package com.somacode.books.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.somacode.books.model.Book

class HomeViewModel : ViewModel() {

    val books = MutableLiveData<MutableList<Book>>().apply {
        value = mutableListOf()
    }
}