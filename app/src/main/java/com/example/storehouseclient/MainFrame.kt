package com.example.storehouseclient

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.storehouseclient.ui.UsersFragment

class MainFrame : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val welcomeText: TextView = findViewById(R.id.welcomeText)
        val fragmentContainer: View = findViewById(R.id.fragment_container)

        welcomeText.setOnClickListener {
            welcomeText.visibility = View.GONE
            fragmentContainer.visibility = View.VISIBLE

            replaceFragment(UsersFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            .replace(R.id.fragment_container, fragment)
            .commit()
    }    }
