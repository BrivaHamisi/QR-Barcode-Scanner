package com.hamisibriva.qrbarcodescanner.ui.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.hamisibriva.qrbarcodescanner.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setViewPagerAdapter()
    }

    private fun setViewPagerAdapter() {
        // In your activity
        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        val adapter = MainPagerAdapter(this)
        viewPager.adapter = adapter

    }
}