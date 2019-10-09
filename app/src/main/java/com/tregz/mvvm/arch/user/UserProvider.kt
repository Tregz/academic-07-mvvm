package com.tregz.mvvm.arch.user

import android.content.*
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import com.tregz.mvvm.arch.user.UserResolver.Companion.BOOLEAN_TYPE
import com.tregz.mvvm.arch.user.UserResolver.Companion.STRING_TYPE

/** Content provider to access user's shared preferences from any process */
class UserProvider : ContentProvider() {

    private val matcher = UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI(AUTHORITY, "*/*", PREFERENCES)
    }

    /* Always return true, indicating that the provider loaded correctly. */
    override fun onCreate(): Boolean = true

    /* Return no type for MIME type */
    override fun getType(uri: Uri): String? = null

    /* Always returns null (no URI) */
    override fun insert(uri: Uri, values: ContentValues?): Uri? = null

    /* Always returns "no rows affected" (0) */
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int = 0

    /* Provide a read only access to the content provider */
    override fun query(uri: Uri, projection: Array<String>?, selection: String?,
                       selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        return when (matcher.match(uri)) {
            PREFERENCES -> {
                val key = uri.pathSegments[0]
                val cursor = MatrixCursor(arrayOf(key))
                context?.getSharedPreferences(UserResolver.TAG, MODE)?.let {
                    if (!it.contains(key)) return cursor
                    cursor.newRow().add(getValue(it, uri, key, uri.pathSegments[1]))
                }
                cursor
            }
            else -> throw IllegalArgumentException("Unsupported uri $uri")
        }
    }

    /* update() always returns "no rows affected" (0) */
    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int = 0

    private fun getValue(sp: SharedPreferences, uri: Uri, key: String, type: String): Any? {
        return when (type) {
            STRING_TYPE -> sp.getString(key, null)
            BOOLEAN_TYPE -> if (sp.getBoolean(key, false)) 1 else 0
            else -> throw IllegalArgumentException("Unsupported type $uri")
        }
    }

    companion object {
        val TAG: String = UserProvider::class.java.simpleName
        const val AUTHORITY: String = "com.tregz.user_provider"
        const val MODE = Context.MODE_PRIVATE
        private const val PREFERENCES = 1
    }
}
