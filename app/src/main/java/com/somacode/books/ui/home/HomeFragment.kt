package com.somacode.books.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Orientation
import com.google.android.material.textfield.TextInputEditText
import com.somacode.books.adapter.BookAdapter
import com.somacode.books.controller.BookActivity
import com.somacode.books.databinding.FragmentHomeBinding
import com.somacode.books.model.Book
import com.somacode.books.service.BookService
import com.somacode.books.service.tool.PaginationTool
import kotlinx.android.synthetic.main.item_book.*

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    val books: MutableList<Book> = mutableListOf()
    var isLoading: Boolean = false
    val isLastPage: Boolean = false
    var search: String = ""

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Config RecyclerView
        val gridLayout = GridLayoutManager(context, 2)
        binding.bookList.layoutManager = gridLayout
        val bookAdapter = BookAdapter(books)
        binding.bookList.adapter = bookAdapter
        binding.bookList.addOnScrollListener(object: PaginationTool(gridLayout) {
            override fun isLoading(): Boolean = isLoading

            override fun isLastPage(): Boolean = isLastPage

            override fun loadMoreItems() {
                isLoading = true
                PAGE++
                getBooks(bookAdapter, PAGE - 1, search)
            }

        })

        bookAdapter.onItemClick = {
            val intent = Intent(context, BookActivity::class.java)
            intent.putExtra("id", books[it].id!!)
            context?.startActivity(intent)
        }

        binding.searchInput.setOnEditorActionListener { v, _, _ ->
            search = v.text.toString()
            if (search.count() > 2) {
                val preSize = books.size
                books.clear()
                bookAdapter.notifyItemRangeRemoved(0, preSize)
                getBooks(bookAdapter, 0, search)
            }
            return@setOnEditorActionListener true
        }

        // Call api
        getBooks(bookAdapter, 0, "")

        return root
    }

    fun getBooks(bookAdapter: BookAdapter, page: Int, search: String) {
        BookService.getBooks(bookAdapter, books, page, 10, search) {
            isLoading = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}