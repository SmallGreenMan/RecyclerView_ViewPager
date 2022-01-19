package com.gmail.avoishel.recyclerview_viewpager_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Switch
import android.widget.Toast
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

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{

            // - дает информацию о текущем занчении позиции скрола или пролистывания
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                Log.d("viewPager onPageScrolled", "$position $positionOffset $positionOffsetPixels")
            }

            // - onPageSelected - Дает номер текущей отображенной страници
            override fun onPageSelected(position: Int) {
                Log.d("viewPager onPageSelected", position.toString())
                Toast.makeText(this@MainActivity,
                    "$position ${viewPager.currentItem}",
                    Toast.LENGTH_SHORT)
                    .show()
            }

            // - Сообщает нам о сотоянии в котором находится скроллер
            //  0 --- scrollStateEagle - ничего не скролится
            //  1 --- scrollStateDragen - это пользователь ращит страницу
            //  2 --- scrollStateSeting - это скроллер долисывает страницу до конца
            override fun onPageScrollStateChanged(state: Int) {
                Log.d("viewPager onPageScrollStateChanged", state.toString())
            }

        })
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