package app.simple.flaner

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import app.simple.flaner.adapter.PagerAdapter
import app.simple.flaner.extension.activities.BaseActivity
import app.simple.flaner.fragments.Knob
import app.simple.flaner.fragments.Switch
import app.simple.flaner.utils.NullSafety.isNull

class MainActivity : BaseActivity() {

    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.app_container)

        val pagerAdapter = PagerAdapter(supportFragmentManager, 0)

        pagerAdapter.addFragment(Switch.newInstance())
        pagerAdapter.addFragment(Knob.newInstance())

        viewPager.adapter = pagerAdapter
    }
}