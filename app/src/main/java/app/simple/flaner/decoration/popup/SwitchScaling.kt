package app.simple.flaner.decoration.popup

import android.view.View
import android.widget.TextView
import app.simple.flaner.R
import app.simple.flaner.decoration.ripple.DynamicRippleTextView
import app.simple.flaner.preferences.MainPreferences

class SwitchScaling(contentView: View, view: View) : BasePopupWindow() {

    init {

        init(contentView, view)

        contentView.findViewById<DynamicRippleTextView>(R.id.popup_low).onClick(LOW)
        contentView.findViewById<DynamicRippleTextView>(R.id.popup_normal).onClick(NORMAL)
        contentView.findViewById<DynamicRippleTextView>(R.id.popup_high).onClick(HIGH)
    }

    private fun TextView.onClick(scaling: Float) {
        this.setOnClickListener {
            MainPreferences.setScaling(scaling)
            dismiss()
        }
    }

    companion object {
        const val LOW = 1.2F
        const val NORMAL = 1.5F
        const val HIGH = 2.0F
    }
}
