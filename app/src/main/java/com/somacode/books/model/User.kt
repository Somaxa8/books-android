package com.somacode.books.model

import java.io.Serializable

class User(
    var id: Long? = null,
    var username: String? = null,
    var name: String? = null,
    var lastname: String? = null,
    var email: String? = null,
    var activated: Boolean? = null
): Serializable