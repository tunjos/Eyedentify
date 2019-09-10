package eyedentify.email.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import eyedentify.email.R
import eyedentify.email.search.SearchActivity
import eyedentify.email.settings.SettingsActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_search -> {
                SearchActivity.startActivityForResult(this, RC_SEARCH)
            }
            R.id.menu_settings -> {
                SettingsActivity.startActivity(this)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val RC_SEARCH = 0
    }
}
