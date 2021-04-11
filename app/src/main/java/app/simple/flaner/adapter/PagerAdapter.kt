package app.simple.flaner.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class PagerAdapter(fragmentManager: FragmentManager, int: Int) : FragmentStatePagerAdapter(fragmentManager, int) {

    private val fragments: ArrayList<Fragment> = ArrayList()

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    fun addFragment(fragment: Fragment) {
        fragments.add(fragment)
    }
}