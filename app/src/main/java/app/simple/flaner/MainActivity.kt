package app.simple.flaner

import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import app.simple.flaner.extension.activities.BaseActivity
import app.simple.flaner.fragments.Switch
import app.simple.flaner.utils.NullSafety.isNull

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState.isNull()) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.app_container, Switch.newInstance(), "switch")
                .commit()
        }
    }
}