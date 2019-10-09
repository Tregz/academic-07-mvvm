package com.tregz.mvvm.core.read

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4ClassRunner::class)
class ReadUtilTest {


    @Test
    fun parse_Letter() {
        assertNull(ReadUtil.parse("L"))
    }
}
