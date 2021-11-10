package com.somacode.books.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.somacode.books.adapter.BookAdapter
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

        val linearLayoutManager = LinearLayoutManager(context)
        binding.bookList.layoutManager = linearLayoutManager
        val bookAdapter = BookAdapter(books)
        binding.bookList.adapter = bookAdapter
        binding.bookList.addOnScrollListener(object: PaginationTool(linearLayoutManager) {
            override fun isLoading(): Boolean = isLoading

            override fun isLastPage(): Boolean = isLastPage

            override fun loadMoreItems() {
                isLoading = true
                PAGE++
                Log.v("Pagina:", "$PAGE")
                getBooks(bookAdapter, PAGE - 1)
            }

        })
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