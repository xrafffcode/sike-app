package com.sike.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.UserHandle
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.sike.app.fragment.ChatFragment
import com.sike.app.fragment.HomeFragment
import com.sike.app.fragment.ScheduleFragment
import com.sike.app.fragment.UserFragment

class HomeActivity : AppCompatActivity() {


    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        val homeFragment = HomeFragment()
        val scheduleFragment = ScheduleFragment()
        val chatFragment = ChatFragment()
        val userFragment = UserFragment()

        makeCurrentFragment(homeFragment)

        val buttom_navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        buttom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_home -> makeCurrentFragment(homeFragment)
                R.id.menu_schedule -> makeCurrentFragment(scheduleFragment)
                R.id.menu_chat -> makeCurrentFragment(chatFragment)
                R.id.menu_user -> makeCurrentFragment(userFragment)
            }

            true
        }

    }

    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fram_nav, fragment)
            commit()
        }
    }
}