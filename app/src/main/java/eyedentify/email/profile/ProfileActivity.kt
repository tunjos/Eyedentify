package eyedentify.email.profile

import android.app.Activity
import android.content.Intent
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import eyedentify.email.R
import eyedentify.email.api.GravatarApi
import eyedentify.email.model.Entry
import kotlinx.android.synthetic.main.activity_profile.*


class ProfileActivity : AppCompatActivity() {
    private lateinit var avatarImageView: ImageView
    private lateinit var fullNameTextView: TextView
    private lateinit var aboutTextView: TextView

    private lateinit var nameTextView: TextView
    private lateinit var surnameTextView: TextView
    private lateinit var akaTextView: TextView
    private lateinit var usernameTextView: TextView

    private lateinit var emailTextView: TextView
    private lateinit var urlTextView: TextView
    private lateinit var locationTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        bindResources()

        val entry = intent.getSerializableExtra(EXTRA_ENTRY) as Entry?
        entry?.let { setProfile(it) }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Save Coming Soon...", Snackbar.LENGTH_LONG)
                .setAction("Save", null).show()
        }
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

    private fun bindResources() {
        avatarImageView = findViewById(R.id.avatar)
        fullNameTextView = findViewById(R.id.fullname)
        aboutTextView = findViewById(R.id.about)

        nameTextView = findViewById(R.id.name)
        surnameTextView = findViewById(R.id.surname)
        akaTextView = findViewById(R.id.aka)
        usernameTextView = findViewById(R.id.username)

        emailTextView = findViewById(R.id.email)
        urlTextView = findViewById(R.id.url)
        locationTextView = findViewById(R.id.location)
    }

    private fun setProfile(entry: Entry) {
        entry.email?.let { setToolbarTitle(it) }

        val colorMatrix = ColorMatrix()
        colorMatrix.setSaturation(0f)
        val filter = ColorMatrixColorFilter(colorMatrix)

        entry.thumbnailUrl?.let { thumbnailUrl ->
            avatarImageView.let {
                it.colorFilter = filter
                Glide.with(this)
                    .load(GravatarApi.generateThumbnailUrl(thumbnailUrl))
                    .into(it)
            }
        }
        entry.name?.formatted?.let { fullNameTextView.text = it }
        entry.aboutMe?.let { aboutTextView.text = it }

        entry.name?.givenName?.let { nameTextView.text = it }
        entry.name?.familyName?.let { surnameTextView.text = it }
        entry.displayName?.let { akaTextView.text = it }
        entry.preferredUsername?.let { usernameTextView.text = it }

        entry.email?.let { emailTextView.text = it }

        val urlSize = entry.urls?.size ?: 0
        if (urlSize > 0) {
            entry.urls?.get(0)?.value?.let { urlTextView.text = it }
        }
        entry.currentLocation?.let { locationTextView.text = it }
    }

    private fun setToolbarTitle(email: String) {
        supportActionBar?.title = email
    }

    companion object {
        private const val EXTRA_ENTRY = "EXTRA_ENTRY"

        fun startActivity(activity: Activity, entry: Entry) {
            val intent = Intent(activity, ProfileActivity::class.java).apply {
                putExtra(EXTRA_ENTRY, entry)
            }
            activity.startActivity(intent)
        }
    }
}
