package app.simple.flaner.preferences

import androidx.annotation.IntRange
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatDelegate
import app.simple.flaner.preferences.SharedPreferences.getSharedPreferences

object AppearancePreferences {

    private const val appCornerRadius = "corner_radius"

    const val accentColor = "app_accent_color"
    const val appTheme = "app_theme"

    // ---------------------------------------------------------------------------------------------------------- //

    fun setAccentColor(int: Int) {
        getSharedPreferences().edit().putInt(accentColor, int).apply()
    }

    fun getAccentColor(): Int {
        return getSharedPreferences().getInt(accentColor, 0)
    }

    // ---------------------------------------------------------------------------------------------------------- //

    fun setCornerRadius(@IntRange(from = 25, to = 400) radius: Int) {
        getSharedPreferences().edit().putInt(appCornerRadius, radius / 5).apply()
    }

    fun getCornerRadius(): Int {
        return getSharedPreferences().getInt(appCornerRadius, 60)
    }

    // ---------------------------------------------------------------------------------------------------------- //

    fun setAppTheme(@NonNull theme: Int) {
        getSharedPreferences().edit().putInt(appTheme, theme).apply()
    }

    fun getAppTheme(): Int {
        return getSharedPreferences().getInt(appTheme, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }
}