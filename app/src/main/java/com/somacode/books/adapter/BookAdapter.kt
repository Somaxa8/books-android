package com.somacode.books.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.somacode.books.R
import com.somacode.books.model.Book
import com.somacode.books.service.tool.ConstantsTool
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_book.view.*

class BookAdapter(val books: MutableList<Book>): RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    class BookViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun render(book: Book) {
            view.book.bookTitle.text = book.title
            book.author?.let { view.book.bookAuthor.text = "(${book.author})" }
            book.cover?.let {
                Picasso.get().load(book.cover!!.url).into(view.bookCover)
            } ?: run {
                Picasso.get().load(ConstantsTool.IMAGE_BACKGROUD).into(view.bookCover)
            }
        }
    }

    var onItemClick: (Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val holder = BookViewHolder(layoutInflater.inflate(R.layout.item_book, parent, false))
        holder.itemView.setOnClickListener { onItemClick(holder.bindingAdapterPosition) }
        return holder
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.render(books[position])
    }

    override fun getItemCount(): Int = books.size

}