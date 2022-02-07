package com.somacode.books.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.somacode.books.R
import com.somacode.books.model.Book
import com.somacode.books.service.tool.ConstantsTool
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_book.view.*
import kotlinx.android.synthetic.main.item_book.view.bookAuthor
import kotlinx.android.synthetic.main.item_book.view.bookTitle
import kotlinx.android.synthetic.main.item_table_book.view.*

class BookTableAdapter(val books: MutableList<Book>): RecyclerView.Adapter<BookTableAdapter.BookViewHolder>() {

    var toogle: Boolean = true


    class BookViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun render(book: Book) {
            if (book.title!!.length > 14) view.bookTitle.text = book.title!!.slice(0..13) + "..."
            else view.bookTitle.text = book.title

            book.author?.let {
                if (it.length > 10) view.bookAuthor.text = it.slice(0..9) + "..."
                else view.bookAuthor.text = it
            }
//            book.createdAt?.let { view.book.bookDate.text = it.toString() }
        }
    }

    var onItemClick: (View) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val holder = BookViewHolder(layoutInflater.inflate(R.layout.item_table_book, parent, false))

        // Background toggle color
        if (toogle) holder.view.background.setTint(ContextCompat.getColor(parent.context, R.color.cardview_light_background))
        toogle = !toogle

        holder.itemView.setOnClickListener { onItemClick(holder.itemView) }
        return holder
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.render(books[position])
    }

    override fun getItemCount(): Int = books.size

}