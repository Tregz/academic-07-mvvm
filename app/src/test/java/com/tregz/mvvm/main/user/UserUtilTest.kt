package com.tregz.mvvm.main.user

import org.junit.Test

import org.junit.Assert.*

class UserUtilTest {


    @Test
    fun emailValidator_isCorrectDotCom() {
        assertTrue(UserUtil.isEmailValid("name@email.com"))
    }

    @Test
    fun emailValidator_isCorrectSubDomain() {
        assertTrue(UserUtil.isEmailValid("name@email.co.uk"))
    }

    @Test
    fun emailValidator_isCorrectNoDomain() {
        assertFalse(UserUtil.isEmailValid("name@email"))
    }

    @Test
    fun emailValidator_isCorrectDoubleDot() {
        assertFalse(UserUtil.isEmailValid("name@email..com"))
    }

    @Test
    fun emailValidator_isCorrectNoUser() {
        assertFalse(UserUtil.isEmailValid("@email.com"))
    }

    @Test
    fun emailValidator_isCorrectEmpty() {
        assertFalse(UserUtil.isEmailValid(""))
    }

    @Test
    fun personNameValidator_isCorrectEmpty() {
        assertFalse(UserUtil.isPersonNameValid(""))
    }

    @Test
    fun dateValidator_isCorrectEmpty() {
        assertFalse(UserUtil.isDateValid(""))
    }

    @Test
    fun dateValidator_isCorrectGoodFormat() {
        assertTrue(UserUtil.isDateValid("10/12/1199"))
    }

    @Test
    fun dateValidator_isCorrectBadFormat() {
        assertFalse(UserUtil.isDateValid("999/99/99"))
    }
}
