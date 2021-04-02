package app.simple.flaner.decoration.popup

import android.view.View
import android.widget.TextView
import app.simple.flaner.R
import app.simple.flaner.decoration.ripple.DynamicRippleTextView
import app.simple.flaner.preferences.MainPreferences

class SwitchTension(contentView: View, view: View) : BasePopupWindow() {

    init {

        init(contentView, view)

        contentView.findViewById<DynamicRippleTextView>(R.id.popup_low).onClick(LOW)
        contentView.findViewById<DynamicRippleTextView>(R.id.popup_normal).onClick(NORMAL)
        contentView.findViewById<DynamicRippleTextView>(R.id.popup_high).onClick(HIGH)
    }

    private fun TextView.onClick(tension: Float) {
        this.setOnClickListener {
            MainPreferences.setTension(tension)
            dismiss()
        }
    }

    companion object {
        const val LOW = 1.5F
        const val NORMAL = 3.0F
        const val HIGH = 5.0F
    }
}
