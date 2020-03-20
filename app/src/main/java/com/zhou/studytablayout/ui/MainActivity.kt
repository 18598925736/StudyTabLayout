package com.zhou.studytablayout.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.zhou.studytablayout.R
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.RuntimeException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = MyPagerAdapter(
            supportFragmentManager
        )
        viewpager.adapter = adapter
        viewpager.offscreenPageLimit = 3
        viewpager.setPageTransformer(true,
            MyPageTransformer(this, adapter.count)
        )
        tabLayout.setupWithViewPager(viewpager)
    }


    class MyPagerAdapter(manager: FragmentManager) :
        FragmentPagerAdapter(manager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getItem(position: Int): Fragment {
            return MyFragment(position)
        }

        override fun getCount(): Int {
            return 10
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return "title$position"
        }

    }
}
