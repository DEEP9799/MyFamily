package com.example.myfamily

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Camera
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.myfamily.DashboardFragment.Companion.newInstance
import com.example.myfamily.databinding.ActivityMainBinding
import com.example.myfamily.guardFragment.Companion.newInstance
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private val permission = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.CAMERA,
        Manifest.permission.READ_CONTACTS,
        Manifest.permission.CALL_PHONE,

        )

    val permissionCode = 78

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        askForPermission()




        binding.bottomBar.setOnNavigationItemSelectedListener { menuitem ->

            when (menuitem.itemId) {
                R.id.nav_guard -> {

                    inflateFragment(guardFragment.newInstance())

                }
                R.id.nav_home -> {

                    inflateFragment(HomeFragment.newInstance())

                }
                R.id.nav_Maps -> {

                    inflateFragment(MapsFragment())

                }
                R.id.nav_profile -> {

                    inflateFragment(ProfileFragment.newInstance())
                }

            }
            true

        }
        binding.bottomBar.selectedItemId = R.id.nav_home

        //Save data on cloud line 78 to 96
     val currentUser = FirebaseAuth.getInstance().currentUser
        val name = currentUser?.displayName.toString()
        val mail = currentUser?.email.toString()
        val phonenumber = currentUser?.phoneNumber.toString()
        val imgUrl = currentUser?.photoUrl.toString()

        val db = Firebase.firestore



        // Create a new user with a first and last name
        val user = hashMapOf(
            "first_name" to name,
            "mail" to mail,
            "phonenumber" to phonenumber,
            "imgUrl" to imgUrl
        )

        db.collection("users").document(mail).set(user).addOnSuccessListener { }.addOnFailureListener { }


    }

    private fun askForPermission() {
        ActivityCompat.requestPermissions(this, permission, permissionCode)

    }


    private fun inflateFragment(newInstance: Fragment) {
        var transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, newInstance)
        transaction.commit()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == permissionCode) {

            if (allPermissionGranted()) {


            } else {

            }


        }
    }


    private fun allPermissionGranted(): Boolean {
        for (item in permission) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    item
                ) !== PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        return true


    }
}


