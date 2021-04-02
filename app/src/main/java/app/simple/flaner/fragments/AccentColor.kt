package app.simple.flaner.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.simple.flaner.R
import app.simple.flaner.adapter.AccentColorAdapter
import app.simple.flaner.extension.fragments.ScopedBottomSheetFragment
import app.simple.flaner.preferences.AppearancePreferences

class AccentColor : ScopedBottomSheetFragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var accentColorAdapter: AccentColorAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = layoutInflater.inflate(R.layout.dialog_color_accent, container, false)

        recyclerView = view.findViewById(R.id.accent_recycler_view)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 4, GridLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(true)

        accentColorAdapter = AccentColorAdapter()
        recyclerView.adapter = accentColorAdapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        accentColorAdapter.setOnPaletteChangeListener(object : AccentColorAdapter.Companion.PalettesAdapterCallbacks {
            override fun onColorPressed(source: Int) {
                AppearancePreferences.setAccentColor(source)
            }
        })
    }

    override fun onPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == AppearancePreferences.accentColor) {
            requireActivity().recreate()
        }
    }

    companion object {
        fun newInstance(): AccentColor {
            val args = Bundle()
            val fragment = AccentColor()
            fragment.arguments = args
            return fragment
        }
    }
}