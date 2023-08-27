package com.example.newsapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.example.newsapps.fragment.BookmarksFragment
import com.example.newsapps.fragment.HeadlinesFragment
import com.example.newsapps.fragment.SettingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottom_navigation: BottomNavigationView
    private lateinit var fragment: FragmentContainerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation = findViewById(R.id.bottom_navigation)
        fragment = findViewById(R.id.fragment_container)

        replaceFragment(HeadlinesFragment())

        bottom_navigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.headlines -> {
                    replaceFragment(HeadlinesFragment())
                }
                R.id.bookmarks -> {
                    replaceFragment(BookmarksFragment())
                }
                R.id.setting -> {
                    replaceFragment(SettingFragment())
                }
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
    }
}