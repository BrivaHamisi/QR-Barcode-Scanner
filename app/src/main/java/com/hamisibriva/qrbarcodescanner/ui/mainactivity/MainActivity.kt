package com.hamisibriva.qrbarcodescanner.ui.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hamisibriva.qrbarcodescanner.R

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Setting Up the ViewPager
        setViewPagerAdapter()

        //Setting Up the bottom Navigation
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        setBottomNavigation()
    }

    private fun setBottomNavigation() {
        bottomNavigationView.setOnClickListener(){

        }
    }

    private fun setViewPagerAdapter() {
        // In your activity
        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        val adapter = MainPagerAdapter(this)
        viewPager.adapter = adapter

    }
}