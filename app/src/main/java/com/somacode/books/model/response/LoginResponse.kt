package com.somacode.books.model.response

import com.google.gson.annotations.SerializedName
import com.somacode.books.model.Authority
import com.somacode.books.model.Token
import com.somacode.books.model.User
import java.io.Serializable

class LoginResponse(
    @SerializedName("oauth2AccessToken")
    var token: Token? = null,
    var user: User? = null,
    var authorities: List<Authority>
): Serializable