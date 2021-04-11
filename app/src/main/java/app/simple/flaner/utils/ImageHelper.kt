package app.simple.flaner.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.core.content.ContextCompat
import java.io.FileNotFoundException
import java.io.InputStream

object ImageHelper {

    /**
     * Converts vector drawable to bitmap
     */
    @JvmStatic
    fun Int.toBitmap(context: Context): Bitmap? {
        val drawable = ContextCompat.getDrawable(context, this)
        val bitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable?.setBounds(0, 0, canvas.width, canvas.height)
        drawable?.draw(canvas)
        return bitmap
    }

    /**
     * Converts vector drawable to bitmap
     */
    @JvmStatic
    fun Int.toBitmap(context: Context, size: Int): Bitmap? {
        val drawable = ContextCompat.getDrawable(context, this)
        val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable?.setBounds(0, 0, canvas.width, canvas.height)
        drawable?.draw(canvas)
        return bitmap
    }

    /**
     * Converts vector to bitmap drawable
     *
     * @return [BitmapDrawable]
     */
    fun Int.toBitmapDrawable(context: Context): BitmapDrawable {
        return BitmapDrawable(context.resources, this.toBitmap(context))
    }

    /**
     * Converts vector to bitmap drawable
     *
     * @return [BitmapDrawable]
     */
    fun Int.toBitmapDrawable(context: Context, size: Int): BitmapDrawable {
        return BitmapDrawable(context.resources, this.toBitmap(context, size))
    }
}