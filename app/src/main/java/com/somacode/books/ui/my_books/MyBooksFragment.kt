package com.somacode.books.ui.my_books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.somacode.books.adapter.BookTableAdapter
import com.somacode.books.databinding.FragmentMyBooksBinding
import com.somacode.books.model.Book
import com.somacode.books.service.BookService
import com.somacode.books.service.tool.PaginationTool
import kotlinx.android.synthetic.main.item_table_book.view.*

class MyBooksFragment : Fragment() {

    private lateinit var myBooksViewModel: MyBooksViewModel
    private var binding: FragmentMyBooksBinding? = null
    val books: MutableList<Book> = mutableListOf()
    var isLoading: Boolean = false
    val isLastPage: Boolean = false
    var search: String = ""
    var displayed: MutableList<View> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        myBooksViewModel = ViewModelProvider(this).get(MyBooksViewModel::class.java)

        binding = FragmentMyBooksBinding.inflate(inflater, container, false)
        val root: View = binding!!.root

        // Config RecyclerView
        val linearLayoutManager = LinearLayoutManager(context)
        binding!!.bookList.layoutManager = linearLayoutManager
        val bookAdapter = BookTableAdapter(books)
        binding!!.bookList.adapter = bookAdapter

        bookAdapter.onItemClick = {
            if (it.cardToogle.visibility != View.GONE) {
                it.cardToogle.visibility = View.GONE
            } else {
                displayed.forEach { v -> v.cardToogle.visibility = View.GONE }
                displayed.clear()
                it.cardToogle.visibility = View.VISIBLE
                displayed.add(it)
            }
        }

        binding!!.bookList.addOnScrollListener(object: PaginationTool(linearLayoutManager) {
            override fun isLoading(): Boolean = isLoading

            override fun isLastPage(): Boolean = isLastPage

            override fun loadMoreItems() {
                isLoading = true
                PAGE++
                getBooks(bookAdapter, PAGE - 1, search)
            }

        })

        getBooks(bookAdapter, 0, "")

        return root
    }

    private fun getBooks(bookAdapter: BookTableAdapter, page: Int, search: String) {
        BookService.getBooks(bookAdapter, books, page, 30, search) {
            isLoading = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}