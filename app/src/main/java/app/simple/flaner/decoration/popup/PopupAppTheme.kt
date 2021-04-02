package app.simple.flaner.decoration.popup

import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import app.simple.flaner.R
import app.simple.flaner.decoration.ripple.DynamicRippleTextView
import app.simple.flaner.preferences.AppearancePreferences

class PopupAppTheme(contentView: View, view: View) : BasePopupWindow() {

    init {
        init(contentView, view)

        contentView.findViewById<DynamicRippleTextView>(R.id.popup_theme_dark).onClick(AppCompatDelegate.MODE_NIGHT_YES)
        contentView.findViewById<DynamicRippleTextView>(R.id.popup_theme_light).onClick(AppCompatDelegate.MODE_NIGHT_NO)
        contentView.findViewById<DynamicRippleTextView>(R.id.popup_theme_follow_system).onClick(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }

    private fun DynamicRippleTextView.onClick(theme: Int) {
        this.setOnClickListener {
            AppearancePreferences.setAppTheme(theme)
            dismiss()
        }
    }
}