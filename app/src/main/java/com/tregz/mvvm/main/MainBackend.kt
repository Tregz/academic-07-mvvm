package com.tregz.mvvm.main

import androidx.lifecycle.ViewModel
import com.tregz.mvvm.data.item.ItemApple
import com.tregz.mvvm.list.ListApple
import java.util.*

class MainBackend(private val view: MainListener) : ViewModel() {

    fun insertApple(ripe: Date?, color: Int): ItemApple {
        view.toast("Pomme ajoutée de couleur: $color")
        return ListApple.add(ItemApple(ripe).apply { this.color = color })
    }

    companion object {
        //private val TAG: String = MainBackend::class.java.simpleName
    }
}
