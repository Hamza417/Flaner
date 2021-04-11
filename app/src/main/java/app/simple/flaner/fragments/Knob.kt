package app.simple.flaner.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import app.simple.flaner.R
import app.simple.flaner.decoration.knob.RotaryKnobListener
import app.simple.flaner.decoration.knob.RotaryKnobView
import app.simple.flaner.extension.fragments.ScopedFragment
import app.simple.flaner.preferences.MainPreferences

class Knob : ScopedFragment() {

    private lateinit var knob: RotaryKnobView
    private lateinit var total: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_knob, container, false)

        knob = view.findViewById(R.id.knob)
        total = view.findViewById(R.id.knob_count)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        knob.setKnobPosition(MainPreferences.getKnobCount())
        knob.haptic = MainPreferences.getHaptic()
        total.text = String.format("%.1f", MainPreferences.getKnobCount()).plus("\u00B0")

        knob.setListener(object : RotaryKnobListener {
            override fun onRotate(value: Float) {
                total.text = String.format("%.1f", value).plus("\u00B0")
                MainPreferences.setKnobCount(value)
            }

            override fun onIncrement(value: Float) {
                /* no-op */
                // TODO - implement incremental value
            }
        })
    }

    override fun onPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when (key) {
            MainPreferences.knobCount -> {
                //total.text = String.format("%.1f", MainPreferences.getKnobCount()).plus("\u00B0")
            }
            MainPreferences.haptic -> {
                knob.haptic = MainPreferences.getHaptic()
            }
        }
    }

    companion object {
        fun newInstance(): Knob {
            val args = Bundle()
            val fragment = Knob()
            fragment.arguments = args
            return fragment
        }
    }
}