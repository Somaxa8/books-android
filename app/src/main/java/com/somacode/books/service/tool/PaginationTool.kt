package com.somacode.books.service.tool

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationTool(val linearLayoutManager: LinearLayoutManager): RecyclerView.OnScrollListener() {

    companion object {
        val SIZE: Int = 10
        var PAGE: Int = 1
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = linearLayoutManager.childCount
        val totalItemCount = linearLayoutManager.itemCount
        val firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()

        if (!isLoading() && !isLastPage()) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0 && totalItemCount >= SIZE) {
                loadMoreItems()
            }
        }
    }

    abstract fun isLoading(): Boolean

    abstract fun isLastPage(): Boolean

    abstract fun loadMoreItems()
}