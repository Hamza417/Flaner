package app.simple.flaner.preferences

import androidx.appcompat.app.AppCompatDelegate
import app.simple.flaner.preferences.SharedPreferences.getSharedPreferences

/**
 * All app preferences
 */
object MainPreferences {

    private const val isSwitchChecked = "is_switch_checked"
    const val scaling = "scaling"
    const val tension = "tension"

    fun setIsSwitchChecked(boolean: Boolean) {
        getSharedPreferences().edit().putBoolean(isSwitchChecked, boolean).apply()
    }

    fun isSwitchChecked(): Boolean {
        return getSharedPreferences().getBoolean(isSwitchChecked, false)
    }

    fun setScaling(value: Float) {
        getSharedPreferences().edit().putFloat(scaling, value).apply()
    }

    fun getScaling(): Float {
        return getSharedPreferences().getFloat(scaling, 1.5F)
    }

    fun setTension(value: Float) {
        getSharedPreferences().edit().putFloat(tension, value).apply()
    }

    fun getTension(): Float {
        return getSharedPreferences().getFloat(tension, 1.5F)
    }
}