package com.example.myfamily

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent




class SplashScreen: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        val isUserLoggedIn = SharedPrf.getBoolean(PrefContants.Is_User_Logged_In)

        if (isUserLoggedIn) {

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }
}