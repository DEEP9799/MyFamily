package com.example.myfamily

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var bottomBar = findViewById<BottomNavigationView>(R.id.bottom_bar)


        bottomBar.setOnNavigationItemSelectedListener { menuitem ->

            if (menuitem.itemId == R.id.nav_guard) {

                inflatefragment(guardFragment.newInstance())

            } else if (menuitem.itemId == R.id.nav_home) {

                inflatefragment(HomeFragment.newInstance())

            } else if(menuitem.itemId==R.id.nav_dashboard){

                inflatefragment(DashboardFragment.newInstance())

            } else if(menuitem.itemId==R.id.nav_profile){

                inflatefragment(ProfileFragment.newInstance())
            }
            true

        }


    }


    private fun inflatefragment(newInstance: Fragment) {

        var transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, newInstance)
        transaction.commit()
    }


}