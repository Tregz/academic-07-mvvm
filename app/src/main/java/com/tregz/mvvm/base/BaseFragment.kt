package com.tregz.mvvm.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment

import com.tregz.mvvm.main.MainListener

abstract class BaseFragment : Fragment() {
    protected lateinit var listener: MainListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, State.ATTACHED.name)
        try {
            listener = context as MainListener
        } catch (e: ClassCastException) {
            val name = MainListener::class.java.simpleName
            throw ClassCastException("$context must implement $name")
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, State.FRAGMENT_CREATED.name)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, State.VIEW_CREATED.name)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, State.ACTIVITY_CREATED.name)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, State.VIEW_DESTROYED.name)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, State.FRAGMENT_DESTROYED.name)
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, State.DETACHED.name)
    }

    enum class State {
        ATTACHED,
        FRAGMENT_CREATED,
        VIEW_INFLATING,
        VIEW_CREATED,
        ACTIVITY_CREATED, // CREATED
        VIEW_DESTROYED,
        FRAGMENT_DESTROYED,
        DETACHED // DESTROYED
    }

    companion object {
        var TAG = BaseFragment::class.java.simpleName
    }
}
