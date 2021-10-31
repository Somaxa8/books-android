package com.somacode.books

import android.app.Application
import android.util.Log
import com.somacode.books.model.response.LoginResponse
import com.somacode.books.service.tool.StorageTool
import java.io.Serializable

class Session: Serializable {

    var loginResponse: LoginResponse? = null


    companion object {

        private const val KEY_SESSION = "SESSION"

        fun create(application: Application): Session {
            val session: Session? = StorageTool.load(application, KEY_SESSION)
            return session ?: Session()
        }

        fun store(application: Application, session: Session) {
            StorageTool.store(application, session, KEY_SESSION)
        }

    }

}