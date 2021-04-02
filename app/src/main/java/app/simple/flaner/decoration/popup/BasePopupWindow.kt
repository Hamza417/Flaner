package app.simple.flaner.decoration.popup

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import app.simple.flaner.R
import app.simple.flaner.utils.ViewUtils

/**
 * A customised version of popup menu that uses [PopupWindow]
 * created to replace ugly material popup menu which does not
 * provide any customizable flexibility. This on the other hand
 * uses custom layout, background, animations and also dims entire
 * window when appears. It is highly recommended to use this
 * and ditch popup menu entirely.
 */
open class BasePopupWindow : PopupWindow() {

    fun init(contentView: View, viewGroup: ViewGroup, xOff: Float, yOff: Float) {
        setContentView(contentView)
        init()
        showAsDropDown(viewGroup, xOff.toInt() - width, yOff.toInt() - height, Gravity.START)
    }

    fun init(contentView: View, view: View) {
        setContentView(contentView)
        init()
        showAsDropDown(view, (width / 1.2F * -1).toInt(), height / 4, Gravity.NO_GRAVITY)
    }

    fun init(contentView: View, view: View, gravity: Int) {
        setContentView(contentView)
        init()
        showAsDropDown(view, (width / 2F * -1).toInt(), 0, gravity)
    }


    private fun init() {
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        contentView.clipToOutline = false
        width = contentView.measuredWidth
        height = contentView.measuredHeight
        animationStyle = R.style.PopupAnimation
        isClippingEnabled = false
        isFocusable = true
        elevation = 50F

        ViewUtils.addShadow(contentView)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            overlapAnchor = false
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            setIsClippedToScreen(false)
            setIsLaidOutInScreen(true)
        }
    }
}
