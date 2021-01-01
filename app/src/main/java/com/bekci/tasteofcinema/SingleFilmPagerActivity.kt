package com.bekci.tasteofcinema

import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bekci.tasteofcinema.singlefilm.SingleFilmFragment

class SingleFilmPagerActivity : FragmentActivity() {
    private lateinit var viewpager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.list_viewpager)

        viewpager = findViewById(R.id.listfrags_viewpager)

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private inner class ScreenSlidePagerAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int {
            return 5
        }
        override fun getItem(position: Int): Fragment {
            return SingleFilmFragment()
        }
    }
}