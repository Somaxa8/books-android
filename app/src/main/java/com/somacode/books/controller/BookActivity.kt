package com.somacode.books.controller

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.somacode.books.R
import com.somacode.books.databinding.ActivityBookBinding
import com.somacode.books.controller.viewmodel.BookViewModel
import com.somacode.books.service.BookService
import com.squareup.picasso.Picasso

class BookActivity : AppCompatActivity() {

    val bookViewModel: BookViewModel by viewModels()
    private var binding: ActivityBookBinding? = null

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

        binding = ActivityBookBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        bookViewModel.book.observe(this, {
            binding!!.bookTitle.text = it.title!!
            binding!!.bookAuthor.text = it.author!!
            binding!!.bookEditorial.text = it.editorial!!
            binding!!.bookLanguage.text = it.language!!.title!!
            binding!!.bookExtension.text = it.book!!.extension!!.uppercase()
            binding!!.bookDescription.text = Html.fromHtml(it.description!!, Html.FROM_HTML_MODE_COMPACT)
            Picasso.get().load(it.cover!!.url).into(binding!!.bookCover)
            it.categories.forEach { category -> addChipCategory(category.title!!, binding!!.chipGroup) }
        })

        binding!!.back.setOnClickListener {
            finish()
        }

        val bookId: Long = intent.extras!!.getLong("id")

        BookService.getBook(this, bookId) {
            println("Ok")
        }
    }

    fun addChipCategory(title: String, chipGroup: ChipGroup) {
        val chip = Chip(this)
        chip.text = title
        chip.setTextColor(resources.getColor(R.color.success, resources.newTheme()))
        chipGroup.addView(chip, chipGroup.childCount - 1)
    }
}