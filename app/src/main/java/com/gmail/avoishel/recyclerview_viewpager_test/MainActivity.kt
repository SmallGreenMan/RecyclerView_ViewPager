package com.gmail.avoishel.recyclerview_viewpager_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.gmail.avoishel.recyclerview_viewpager_test.fragments.Fragment_first
import com.gmail.avoishel.recyclerview_viewpager_test.fragments.Fragment_second
import com.gmail.avoishel.recyclerview_viewpager_test.fragments.Fragment_third

class MainActivity : AppCompatActivity() {

    private lateinit var nightTeamSwitch: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nightTeamSwitch = findViewById(R.id.nightTheamSwitch)
        nightTeamSwitch.setOnClickListener {
            if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            else
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        val viewPager : ViewPager =  findViewById<ViewPager>(R.id.viewPager)
        viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
    }

    inner class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm){
        override fun getCount(): Int {
            return 3
        }

        override fun getItem(position: Int): Fragment {
            return when(position){
                0 -> Fragment_first()
                1 -> Fragment_second()
                else -> Fragment_third()
            }
        }

    }
}