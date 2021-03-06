package co.imdbreviews.views.splash

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import co.imdbreviews.R
import com.google.android.material.snackbar.BaseTransientBottomBar

import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", BaseTransientBottomBar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

}
