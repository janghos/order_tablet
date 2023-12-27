package com.example.order_tablet

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.example.order_tablet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //1. 액션바대신에 툴바로 대체한다.
        setSupportActionBar(binding.toolbar)
        //2. ActionBarDrawerToggle 버튼적용
        toggle = ActionBarDrawerToggle(this,binding.drawerlayout, R.string.navigation_drawer_open,  R.string.navigation_drawer_close)
        //3.업버튼 활성화
        //4. 토글 sync
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
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

    //5. 이 함수가 있어야 토글버튼을 누르면 Drawer가 들어갔다 나갔다한다.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun replaceFragment(fragment : Fragment) {
        try {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_display, fragment)
                .commit()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}