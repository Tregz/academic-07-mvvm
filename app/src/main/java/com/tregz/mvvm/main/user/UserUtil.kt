package com.tregz.mvvm.main.user

import java.util.regex.Pattern

object UserUtil {

    fun isEmailValid(input: String?): Boolean {
        return input != null && input.matches("\\w+(\\.\\w+)*@[a-z]+(\\.[a-z]+)+".toRegex())
    }

    fun isPersonNameValid(input: String?): Boolean {
        val pattern = Pattern.compile("^[a-zA-Z\\s]*$")
        return input != null && !input.isEmpty() && pattern.matcher(input).matches()
    }

    fun isDateValid(input: String?): Boolean {
        val skeleton = "^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$"
        val pattern = Pattern.compile(skeleton)
        return input != null && !input.isEmpty() && pattern.matcher(input).matches()
    }

    fun isPhoneValid(input: String?): Boolean {
        val skeleton = "/\\(?([0-9]{3})\\)?([ .-]?)([0-9]{3})\\2([0-9]{4})/"
        val pattern = Pattern.compile(skeleton)
        return input != null && !input.isEmpty() && pattern.matcher(input).matches()
    }
}