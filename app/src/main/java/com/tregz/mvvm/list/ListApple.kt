package com.tregz.mvvm.list

import com.tregz.mvvm.data.item.ItemApple

object ListApple {

    private val list = mutableListOf<ItemApple>()
    private val set = mutableSetOf<ItemApple>()
    private val map = mutableMapOf<Int, ItemApple>()

    val listCount: Int
        get() = list.size

    val setCount: Int
        get() = set.size

    fun add(apple: ItemApple): ItemApple {
        list.add(apple)
        set.add(apple)
        map[map.size] = apple
        return apple
    }

    fun clear() {
        list.clear()
        set.clear()
        map.clear()
    }
}