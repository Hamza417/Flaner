package app.simple.flaner.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import app.simple.flaner.R
import app.simple.flaner.extension.fragments.ScopedFragment
import app.simple.flaner.preferences.MainPreferences
import app.simple.flaner.switch.SwitchView
import app.simple.inure.decorations.switch.SwitchCallbacks

class Switch : ScopedFragment() {

    private lateinit var switchView: SwitchView
    private lateinit var settings: ImageButton
    private lateinit var count: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = layoutInflater.inflate(R.layout.fragment_switch, container, false)

        switchView = view.findViewById(R.id.switchView)
        settings = view.findViewById(R.id.app_settings)
        count = view.findViewById(R.id.count)

        switchView.tension = MainPreferences.getTension()
        switchView.scaling = MainPreferences.getScaling()
        switchView.haptic = MainPreferences.getHaptic()
        switchView.sound = MainPreferences.getSound()

        count.text = MainPreferences.getCount().toString()
        switchView.setChecked(MainPreferences.isSwitchChecked())

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        switchView.setOnSwitchCheckedChangeListener(object : SwitchCallbacks {
            override fun onCheckedChanged(isChecked: Boolean) {
                MainPreferences.setIsSwitchChecked(isChecked)
                MainPreferences.setCount(MainPreferences.getCount() + 1)
            }
        })

        settings.setOnClickListener {
            Prefs.newInstance()
                .show(childFragmentManager, "prefs")
        }
    }

    override fun onPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when (key) {
            MainPreferences.tension -> {
                switchView.tension = MainPreferences.getTension()
            }
            MainPreferences.scaling -> {
                switchView.scaling = MainPreferences.getScaling()
            }
            MainPreferences.haptic -> {
                switchView.haptic = MainPreferences.getHaptic()
            }
            MainPreferences.sound -> {
                switchView.sound = MainPreferences.getSound()
            }
            MainPreferences.count -> {
                count.text = MainPreferences.getCount().toString()
            }
        }
    }

    companion object {
        fun newInstance(): Switch {
            val args = Bundle()
            val fragment = Switch()
            fragment.arguments = args
            return fragment
        }
    }
}