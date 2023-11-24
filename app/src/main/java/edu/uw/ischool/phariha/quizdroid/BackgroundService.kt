package edu.uw.ischool.phariha.quizdroid
import android.app.IntentService
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.preference.PreferenceManager

class BackgroundService : IntentService("BackgroundService") {

    private val handler = Handler(Looper.getMainLooper())

    override fun onHandleIntent(intent: Intent?) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val url = sharedPreferences.getString("pref_key_url", "")

        handler.post {
            Toast.makeText(
                applicationContext,
                "Downloading from URL: $url",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}