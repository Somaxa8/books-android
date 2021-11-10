package com.somacode.books.model

class Document(
    var id: Long? = null,
    var name: String? = null,
    var baseName: String? = null,
    var extension: String? = null,
    var description: String? = null,
    var tag: String? = null,
    var type: Type? = null,
    var url: String? = null
) {

    enum class Type { IMAGE, DOCUMENT }

}