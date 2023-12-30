package com.example.order_tablet

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.core.view.get
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.order_tablet.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    private lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.contentMain.toolbar.ibToolbar.setOnClickListener {
            toggleDrawerLayout(binding.root)
        }

        binding.navView.setNavigationItemSelectedListener(this)

        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_item1 -> {
                    replaceFragment(MainMenuFragment())
                    true
                }
                R.id.nav_item2 -> {
                    replaceFragment(SideMenuFragment())
                    true
                }
                // 다른 아이템들에 대한 처리 추가
                else -> false
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Log.d(TAG, "onNavigationItemSelected: ${item.title}")
        return true
    }

    private fun toggleDrawerLayout(drawerLayout: DrawerLayout) {

        if(!drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        else {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

    fun replaceFragment(fragment : Fragment) {
        try {
            supportFragmentManager.beginTransaction()
                .replace(R.id.content_main, fragment)
                .commit()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}