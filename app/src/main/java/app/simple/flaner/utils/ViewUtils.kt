package app.simple.flaner.utils

import android.view.View
import app.simple.flaner.R
import app.simple.flaner.utils.ColorUtils.resolveAttrColor
import app.simple.flaner.preferences.AppearancePreferences

object ViewUtils {

    // @RequiresApi(28)
    /**
     * Adds outline shadows to the view using the accent color
     * of the app
     *
     * @param contentView [View] that needs to be elevated with colored
     *                    shadow
     */
    fun addShadow(contentView: View) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            contentView.outlineAmbientShadowColor =
                contentView.context.resolveAttrColor(R.attr.colorAppAccent)
            contentView.outlineSpotShadowColor =
                contentView.context.resolveAttrColor(R.attr.colorAppAccent)
        }
    }

    /**
     * Makes the view go away
     */
    fun View.makeGoAway() {
        this.visibility = View.GONE
    }

    /**
     * Makes the view come back
     */
    fun View.makeVisible() {
        this.visibility = View.VISIBLE
    }
}
