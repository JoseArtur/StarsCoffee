package com.evanemran.quickfoods.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class FoodsViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
private val fragments = ArrayList<Fragment>()
private val fragTitle = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
        return fragments[position]
        }

        override fun getCount(): Int {
        return fragments.size
        }

        fun addFragment(fragment: Fragment, title: String) {
        fragments.add(fragment)
        fragTitle.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
        return fragTitle[position]
        }
        }