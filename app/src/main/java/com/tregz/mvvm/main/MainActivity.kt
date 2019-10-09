package com.tregz.mvvm.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.tregz.mvvm.R
import com.tregz.mvvm.base.BaseActivity

class MainActivity : BaseActivity(), MainListener {

    private var icPerson: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.login -> {
                icPerson = item
                if (!back()) {
                    val action = R.id.action_mainFragment_to_authFragment
                    MainNavigation.getInstance().navigate(this, action)
                    item.setIcon(R.drawable.ic_arrow_back)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        back()
    }

    private fun back(): Boolean {
        return (MainNavigation.getInstance().fragmentId(this) != R.id.mainFragment).apply {
            if (this) {
                MainNavigation.getInstance().pop(this@MainActivity)
                icPerson?.setIcon(R.drawable.ic_person)
            }
        }
    }

    override fun onFragmentStart(title: String) {
        setTitle(title)
    }

    override fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        //private val TAG: String = MainActivity::class.java.simpleName
    }
}
