package app.simple.flaner.switch

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.media.SoundPool
import android.os.VibrationEffect
import android.os.Vibrator
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("ClickableViewAccessibility")
class SwitchView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : SwitchBackground(context, attrs, defStyleAttr) {

    private var vibration: Vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    private val soundPool = SoundPool.Builder().setMaxStreams(1).build()
    private val soundId = soundPool.load(context, R.raw.boop, 1)

    private var thumb: SwitchForeground
    private var track: SwitchBackground
    private var switchCallbacks: SwitchCallbacks? = null

    private var isChecked: Boolean = false

    var haptic = false
    var sound = false
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

        feedback()
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

        feedback()
    }

    private fun feedback() {
        CoroutineScope(Dispatchers.Default).launch {
            if (haptic) {
                if (vibration.hasVibrator())
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        vibration.vibrate(VibrationEffect.createOneShot((25 / (tension / 5)).toLong(), 20))
                    } else {
                        @Suppress("deprecation")
                        vibration.vibrate((50 / (tension / 5)).toLong())
                    }
            }

            if (sound) {
                soundPool.play(
                    soundId,
                    1F,
                    1F,
                    1,
                    0,
                    tension / 5F
                )
            }
        }
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