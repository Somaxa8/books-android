package com.somacode.books.model

import java.io.Serializable
import java.time.LocalDate

abstract class Auditing(
    var createdAt: LocalDate? = null,
    var createdBy: User? = null
)