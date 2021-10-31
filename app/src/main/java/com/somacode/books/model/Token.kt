package com.somacode.books.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Token(
    @SerializedName("access_token")
    var accessToken: String? = null,
    @SerializedName("refresh_token")
    var refreshToken: String? = null
): Serializable