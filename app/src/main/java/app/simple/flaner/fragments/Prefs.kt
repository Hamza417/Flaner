package app.simple.flaner.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatDelegate
import app.simple.flaner.R
import app.simple.flaner.decoration.corners.DynamicCornerLinearLayout
import app.simple.flaner.decoration.popup.PopupAppTheme
import app.simple.flaner.decoration.popup.SwitchScaling
import app.simple.flaner.decoration.popup.SwitchTension
import app.simple.flaner.decoration.ripple.DynamicRippleTextView
import app.simple.flaner.extension.fragments.ScopedBottomSheetFragment
import app.simple.flaner.preferences.AppearancePreferences
import app.simple.flaner.preferences.MainPreferences
import app.simple.flaner.utils.ThemeSetter

class Prefs : ScopedBottomSheetFragment() {

    private lateinit var scaling: DynamicRippleTextView
    private lateinit var tension: DynamicRippleTextView
    private lateinit var appTheme: DynamicRippleTextView
    private lateinit var appColor: ImageButton
    private lateinit var sound: CheckBox
    private lateinit var haptic: CheckBox

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = layoutInflater.inflate(R.layout.dialog_prefs, container, false)

        scaling = view.findViewById(R.id.switch_scaling)
        tension = view.findViewById(R.id.switch_tension)
        appTheme = view.findViewById(R.id.app_theme)
        appColor = view.findViewById(R.id.app_accent_color)
        sound = view.findViewById(R.id.sound_effect_checkbox)
        haptic = view.findViewById(R.id.haptic_feedback_checkbox)

        sound.isChecked = MainPreferences.getSound()
        haptic.isChecked = MainPreferences.getHaptic()

        setScalingText()
        setTensionText()
        setThemeText()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        scaling.setOnClickListener {
            SwitchScaling(
                layoutInflater.inflate(R.layout.popup_switch_prefs, DynamicCornerLinearLayout(requireContext()), true),
                scaling
            )
        }

        tension.setOnClickListener {
            SwitchTension(
                layoutInflater.inflate(R.layout.popup_switch_prefs, DynamicCornerLinearLayout(requireContext()), true),
                tension
            )
        }

        appTheme.setOnClickListener {
            PopupAppTheme(
                layoutInflater.inflate(R.layout.popup_application_theme, DynamicCornerLinearLayout(requireContext()), true),
                appTheme
            )
        }

        appColor.setOnClickListener {
            AccentColor.newInstance()
                .show(requireFragmentManager(), "color")
            this.dialog?.dismiss()
        }

        haptic.setOnCheckedChangeListener { _, isChecked ->
            MainPreferences.setHaptic(isChecked)
        }

        sound.setOnCheckedChangeListener { _, isChecked ->
            MainPreferences.setSound(isChecked)
        }
    }

    private fun setTensionText() {
        tension.text = when (MainPreferences.getTension()) {
            SwitchTension.LOW -> getString(R.string.low)
            SwitchTension.NORMAL -> getString(R.string.normal)
            SwitchTension.HIGH -> getString(R.string.high)
            else -> getString(R.string.normal)
        }
    }

    private fun setScalingText() {
        scaling.text = when (MainPreferences.getScaling()) {
            SwitchScaling.LOW -> getString(R.string.low)
            SwitchScaling.NORMAL -> getString(R.string.normal)
            SwitchScaling.HIGH -> getString(R.string.high)
            else -> getString(R.string.normal)
        }
    }

    private fun setThemeText() {
        appTheme.text = when (AppearancePreferences.getAppTheme()) {
            AppCompatDelegate.MODE_NIGHT_NO -> getString(R.string.light)
            AppCompatDelegate.MODE_NIGHT_YES -> getString(R.string.dark)
            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM -> getString(R.string.follow_system)
            else -> getString(R.string.normal)
        }
    }

    override fun onPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when (key) {
            MainPreferences.tension -> {
                setTensionText()
            }
            MainPreferences.scaling -> {
                setScalingText()
            }
            AppearancePreferences.appTheme -> {
                setThemeText()
                ThemeSetter.setAppTheme(AppearancePreferences.getAppTheme())
            }
        }
    }

    companion object {
        fun newInstance(): Prefs {
            val args = Bundle()
            val fragment = Prefs()
            fragment.arguments = args
            return fragment
        }
    }
}
