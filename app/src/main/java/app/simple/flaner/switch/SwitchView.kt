package app.simple.flaner.switch

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.animation.DecelerateInterpolator
import android.view.animation.OvershootInterpolator
import androidx.core.content.ContextCompat
import app.simple.flaner.R
import app.simple.flaner.decoration.ripple.Utils
import app.simple.flaner.utils.ColorUtils.resolveAttrColor
import app.simple.flaner.utils.ViewUtils
import app.simple.inure.decorations.switch.SwitchCallbacks
import com.google.android.material.card.MaterialCardView

@SuppressLint("ClickableViewAccessibility")
class SwitchView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : SwitchFrameLayout(context, attrs, defStyleAttr) {

    private var thumb: MaterialCardView
    private var track: SwitchFrameLayout
    private var switchCallbacks: SwitchCallbacks? = null

    private var isChecked: Boolean = false
    var scaling = 1.5F
    var tension = 1.5F

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.switch_view, this, true)

        thumb = view.findViewById(R.id.switch_thumb)
        track = view.findViewById(R.id.switch_track)

        ViewUtils.addShadow(thumb)
        ViewUtils.addShadow(track)

        view.setOnClickListener {
            isChecked = if (isChecked) {
                animateUnchecked()
                false
            } else {
                animateChecked()
                true
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                thumb.animate()
                    .scaleY(scaling)
                    .scaleX(scaling)
                    .setInterpolator(DecelerateInterpolator(1.5F))
                    .setDuration(500L)
                    .start()
            }
            MotionEvent.ACTION_MOVE,
            MotionEvent.ACTION_UP,
            -> {
                thumb.animate()
                    .scaleY(1.0F)
                    .scaleX(1.0F)
                    .setInterpolator(DecelerateInterpolator(1.5F))
                    .setDuration(500L)
                    .start()
            }
        }

        return super.onTouchEvent(event)
    }

    fun setChecked(boolean: Boolean) {
        isChecked = if (boolean) {
            animateChecked()
            boolean
        } else {
            animateUnchecked()
            boolean
        }
    }

    private fun animateUnchecked() {
        thumb.animate()
            .translationX(0F)
            .setInterpolator(OvershootInterpolator(tension))
            .setDuration(500)
            .start()

        Utils.animateBackground(ContextCompat.getColor(context, R.color.switchTrackOff), track)
        switchCallbacks?.onCheckedChanged(false)
        animateElevation(0F)
    }

    private fun animateChecked() {

        val w = context.resources.getDimensionPixelOffset(R.dimen.switch_width)
        val p = context.resources.getDimensionPixelOffset(R.dimen.switch_padding)
        val thumbWidth = context.resources.getDimensionPixelOffset(R.dimen.switch_thumb_dimensions)

        thumb.animate()
            .translationX((w - p * 2 - thumbWidth).toFloat())
            .setInterpolator(OvershootInterpolator(tension))
            .setDuration(500)
            .start()

        Utils.animateBackground(context.resolveAttrColor(R.attr.colorAppAccent), track)
        switchCallbacks?.onCheckedChanged(true)
        animateElevation(100F)
    }

    private fun animateElevation(elevation: Float) {
        val valueAnimator = ValueAnimator.ofFloat(track.elevation, elevation)
        valueAnimator.duration = 500L
        valueAnimator.interpolator = DecelerateInterpolator(1.5F)
        valueAnimator.addUpdateListener {
            track.elevation = it.animatedValue as Float
        }
        valueAnimator.start()
    }

    fun setOnSwitchCheckedChangeListener(switchCallbacks: SwitchCallbacks) {
        this.switchCallbacks = switchCallbacks
    }
}