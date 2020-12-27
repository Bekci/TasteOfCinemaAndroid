package com.bekci.tasteofcinema

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bekci.tasteofcinema.home.HomePageFragment
import com.bekci.tasteofcinema.util.ActivityUtil

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        ActivityUtil.changeFragment(HomePageFragment(), HomePageFragment.TAG, supportFragmentManager, R.id.act_main_fl_container, false)
    }
}
