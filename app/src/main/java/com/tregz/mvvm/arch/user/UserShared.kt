package com.tregz.mvvm.arch.user

import android.content.Context
import android.content.SharedPreferences
import com.tregz.mvvm.arch.user.UserProvider.Companion.AUTHORITY
import com.tregz.mvvm.arch.user.UserProvider.Companion.MODE

class UserShared(private val context: Context) {

    private val editor: SharedPreferences.Editor
        get() = context.getSharedPreferences(UserResolver.TAG, MODE).edit()

    private val resolver: UserResolver = UserResolver(context, AUTHORITY)

    private val prefs = listOf(EMAIL)

    var email: String
        get() = resolver.getString(EMAIL, "")
        set(email) = editor.putString(EMAIL, email).apply()

    fun removePreferences() {
        with(editor) {
            for (str in prefs) remove(str)
            apply()
        }
    }

    companion object {
        val TAG: String = UserShared::class.java.simpleName
        private const val EMAIL = "authentication_email"
    }
}
