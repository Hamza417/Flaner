package app.simple.flaner.adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import app.simple.flaner.R
import app.simple.flaner.decoration.corners.DynamicCornerLinearLayout
import app.simple.flaner.preferences.AppearancePreferences
import org.jetbrains.annotations.NotNull

class AccentColorAdapter : RecyclerView.Adapter<AccentColorAdapter.Holder>() {

    private var list = arrayListOf<Int>()
    private lateinit var palettesAdapterCallbacks: PalettesAdapterCallbacks

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        list = arrayListOf(
            ContextCompat.getColor(parent.context, R.color.inure),
            ContextCompat.getColor(parent.context, R.color.blue),
            ContextCompat.getColor(parent.context, R.color.blueGrey),
            ContextCompat.getColor(parent.context, R.color.darkBlue),
            ContextCompat.getColor(parent.context, R.color.red),
            ContextCompat.getColor(parent.context, R.color.green),
            ContextCompat.getColor(parent.context, R.color.orange),
            ContextCompat.getColor(parent.context, R.color.purple),
            ContextCompat.getColor(parent.context, R.color.brown),
            ContextCompat.getColor(parent.context, R.color.caribbeanGreen),
            ContextCompat.getColor(parent.context, R.color.persianGreen),
            ContextCompat.getColor(parent.context, R.color.amaranth)
        )

        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_color_pallete, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.color.backgroundTintList = ColorStateList.valueOf(list[position])

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            holder.color.outlineSpotShadowColor = list[position]
            holder.color.outlineAmbientShadowColor = list[position]
        }

        holder.color.setOnClickListener {
            palettesAdapterCallbacks.onColorPressed(list[position])
        }

        holder.tick.visibility = if (list[position] == AppearancePreferences.getAccentColor()) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }
    }

    override fun getItemCount(): Int {
        return 12
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val color: DynamicCornerLinearLayout = itemView.findViewById(R.id.adapter_palette_color)
        val tick: ImageView = itemView.findViewById(R.id.adapter_palette_tick)
    }

    fun setOnPaletteChangeListener(palettesAdapterCallbacks: PalettesAdapterCallbacks) {
        this.palettesAdapterCallbacks = palettesAdapterCallbacks
    }

    companion object {
        interface PalettesAdapterCallbacks {
            fun onColorPressed(@NotNull source: Int)
        }
    }
}
