package com.somacode.books.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Orientation
import com.somacode.books.adapter.BookAdapter
import com.somacode.books.controller.BookActivity
import com.somacode.books.databinding.FragmentHomeBinding
import com.somacode.books.model.Book
import com.somacode.books.service.BookService
import com.somacode.books.service.tool.PaginationTool

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    val books: MutableList<Book> = mutableListOf()
    var isLoading: Boolean = false
    val isLastPage: Boolean = false

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
                getBooks(bookAdapter, PAGE - 1)
            }

        })

        bookAdapter.onItemClick = {
            val intent = Intent(context, BookActivity::class.java)
            intent.putExtra("id", books[it].id!!)
            context?.startActivity(intent)
        }

        // Call api
        getBooks(bookAdapter, 0)

        return root
    }

    fun getBooks(bookAdapter: BookAdapter, page: Int) {
        BookService.getBooks(bookAdapter, books, page, 10) {
            isLoading = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}