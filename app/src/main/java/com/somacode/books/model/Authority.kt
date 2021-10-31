package com.somacode.books.model

import java.io.Serializable

class Authority(
    var name: Name? = null,
    var description: String? = null
): Serializable {
    enum class Name {
        ADMIN, USER
    }
}