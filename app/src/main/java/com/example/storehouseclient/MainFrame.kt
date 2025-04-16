package com.example.storehouseclient

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.storehouseclient.ui.UsersFragment

class MainFrame : AppCompatActivity() {

    private val timeout: Long = 600_000
    private val handler = Handler(Looper.getMainLooper())
    private val inactivityRunnable = Runnable {
        restartApp()
    }

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

        resetInactivityTimer()
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun restartApp() {
        val intent = Intent(this, MainFrame::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }

    private fun resetInactivityTimer() {
        handler.removeCallbacks(inactivityRunnable)
        handler.postDelayed(inactivityRunnable, timeout)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        resetInactivityTimer()
        return super.dispatchTouchEvent(ev)
    }

    override fun onResume() {
        super.onResume()
        resetInactivityTimer()
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(inactivityRunnable)
    }
}
