package com.hamisibriva.qrbarcodescanner.ui.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hamisibriva.qrbarcodescanner.R
import com.hamisibriva.qrbarcodescanner.ui.scanner.QrScannerFragment

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.viewPager)

        //Setting Up the ViewPager
        setViewPagerAdapter()

        //Setting Up the bottom Navigation
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        setBottomNavigation()
        
        //Set the ViewPager Listener
        setViewPagerListener()
    }

    private fun setViewPagerListener() {
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                bottomNavigationView.selectedItemId = when(position){
                    0 -> R.id.scanMenuId
                    1 -> R.id.recentScannedMenuId
                    2 -> R.id.favouritesMenuId
                    else -> R.id.scanMenuId
                }
            }
        })
    }

    private fun setBottomNavigation() {
        bottomNavigationView.setOnItemSelectedListener{
            viewPager.currentItem = when (it.itemId) {
                R.id.scanMenuId -> 0
                R.id.recentScannedMenuId -> 1
                R.id.favouritesMenuId -> 2
                else -> 0
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun setViewPagerAdapter() {
        // In your activity
        val adapter = MainPagerAdapter(this)
        viewPager.adapter = adapter

    }
}