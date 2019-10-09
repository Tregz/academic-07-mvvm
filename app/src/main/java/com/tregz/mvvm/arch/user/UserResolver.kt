package com.tregz.mvvm.arch.user

import android.content.Context
import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri

class UserResolver(context: Context, private val authority: String) {

    private val resolver: ContentResolver = context.contentResolver

    private fun getContentUri(key: String, type: String): Uri {
        val content = "content://$authority"
        return Uri.parse(content).buildUpon().appendPath(key).appendPath(type).build()
    }

    private fun getStringValue(cursor: Cursor?, def: String): String {
        if (cursor == null) return def
        var value = def
        if (cursor.moveToFirst()) value = cursor.getString(0)
        cursor.close()
        return value
    }

    private fun getBooleanValue(cursor: Cursor?, def: Boolean): Boolean {
        if (cursor == null) return def
        var value = def
        if (cursor.moveToFirst()) value = cursor.getInt(0) > 0
        cursor.close()
        return value
    }

    fun getString(key: String, def: String): String {
        return getStringValue(query(getContentUri(key, STRING_TYPE)), def)
    }

    fun getBoolean(key: String, def: Boolean): Boolean {
        return getBooleanValue(query(getContentUri(key, BOOLEAN_TYPE)), def)
    }

    private fun query(uri: Uri): Cursor? {
        return resolver.query(uri, null, null, null, null)
    }

    companion object {
        val TAG: String = UserResolver::class.java.simpleName
        const val BOOLEAN_TYPE = "boolean"
        const val STRING_TYPE = "string"
    }
}