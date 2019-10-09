package com.tregz.mvvm.arch.bind

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BindFactory<T>(val creator: () -> T) : ViewModelProvider.Factory {

    @Suppress(WARN)
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return creator() as T
    }

    companion object {
        const val WARN = "UNCHECKED_CAST"
    }
}