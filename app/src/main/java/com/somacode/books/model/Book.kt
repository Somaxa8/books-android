package com.somacode.books.model

import java.time.LocalDate
import java.io.Serializable

class Book(
    var id: Long? = null,
    var title: String? = null,
    var author: String? = null,
    var editorial: String? = null,
    var description: String? = null,
    var date: LocalDate? = null,
    var book: Document? = null,
    var cover: Document? = null,
    var language: Language? = null,
    var categories: List<BookCategory> = mutableListOf(),
): Serializable