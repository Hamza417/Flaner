package app.simple.flaner.preferences

import app.simple.flaner.preferences.SharedPreferences.getSharedPreferences

/**
 * All app preferences
 */
object MainPreferences {

    private const val isSwitchChecked = "is_switch_checked"
    const val scaling = "scaling"
    const val tension = "tension"
    const val haptic = "is_haptic_on"
    const val sound = "is_sound_on"
    const val count = "count"

    fun setIsSwitchChecked(boolean: Boolean) {
        getSharedPreferences().edit().putBoolean(isSwitchChecked, boolean).apply()
    }

    fun isSwitchChecked(): Boolean {
        return getSharedPreferences().getBoolean(isSwitchChecked, false)
    }

    fun setHaptic(boolean: Boolean) {
        getSharedPreferences().edit().putBoolean(haptic, boolean).apply()
    }

    fun getHaptic(): Boolean {
        return getSharedPreferences().getBoolean(haptic, false)
    }

    fun setSound(boolean: Boolean) {
        getSharedPreferences().edit().putBoolean(sound, boolean).apply()
    }

    fun getSound(): Boolean {
        return getSharedPreferences().getBoolean(sound, true)
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

    fun setCount(value: Int) {
        getSharedPreferences().edit().putInt(count, value).apply()
    }

    fun getCount(): Int {
        return getSharedPreferences().getInt(count, 0)
    }
}
