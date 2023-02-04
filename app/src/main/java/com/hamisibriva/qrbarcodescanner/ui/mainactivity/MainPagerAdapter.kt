package com.hamisibriva.qrbarcodescanner.ui.mainactivity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hamisibriva.qrbarcodescanner.ui.scanned_history.ScannedHistoryFragment
import com.hamisibriva.qrbarcodescanner.ui.scanner.QrScannerFragment

class MainPagerAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> QrScannerFragment.newInstance()
            1-> ScannedHistoryFragment.newInstance()
            2-> ScannedHistoryFragment.newInstance()
            else -> QrScannerFragment.newInstance()
        }
    }
}