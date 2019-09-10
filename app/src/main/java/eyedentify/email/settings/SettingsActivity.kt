package eyedentify.email.settings

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import eyedentify.email.BuildConfig
import eyedentify.email.R

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        setSupportActionBar(findViewById(R.id.toolbar))

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings, SettingsFragment())
            .commit()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
            setVersionPreference()
        }

        private fun setVersionPreference() {
            val versionPreference = findPreference(PREFERENCE_VERSION) as Preference?
            val versionName = BuildConfig.VERSION_NAME
            versionPreference?.summary = versionName
        }
    }

    companion object {

        private const val PREFERENCE_VERSION = "version"

        fun startActivity(activity: Activity) {
            val intent = Intent(activity, SettingsActivity::class.java)
            activity.startActivity(intent)
        }
    }
}
