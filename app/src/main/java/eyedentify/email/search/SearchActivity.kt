package eyedentify.email.search

import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import eyedentify.email.R
import eyedentify.email.model.Entry
import eyedentify.email.search.adapters.SearchAdapter
import eyedentify.email.search.presenters.SearchPresenter
import kotlinx.android.synthetic.main.activity_search.*
import org.koin.android.ext.android.inject

class SearchActivity : AppCompatActivity(), eyedentify.email.search.views.SearchView,
    SearchAdapter.ClickListener {

    private val imm: InputMethodManager? by lazy {
        getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    }

    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    private val searchPresenter: SearchPresenter by inject()
    private val searchAdapter: SearchAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        searchView = findViewById(R.id.search_view)
        recyclerView = findViewById(R.id.recycler_view)
        progressBar = findViewById(R.id.progress_bar)

        setupSearchView()
        setupRecyclerView()
        searchPresenter.attachView(this)

        handleIntent(intent)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleIntent(intent)
    }

    override fun onDestroy() {
        searchPresenter.detachView()
        super.onDestroy()
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action && intent.hasExtra(SearchManager.QUERY)) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            if (!TextUtils.isEmpty(query)) {
                searchView.setQuery(query, false)
                // TODO Validate email
                // Perform Search
            }
        }
    }

    private fun setupSearchView() {
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    // TODO Validate email
                    clearSearchResult()
                    hideKeyboard()
                    query?.let {
                        searchPresenter.getProfile(it)
                    } ?: false

                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    return true
                }
            })
        }
    }

    private fun setupRecyclerView() {
        recyclerView.apply {
            setHasFixedSize(true)
            adapter = searchAdapter
        }
        searchAdapter.setClickListener(this)
    }

    private fun clearSearchResult() {
        searchAdapter.setItems(emptyList())
    }

    companion object {

        fun startActivityForResult(activity: Activity, requestCode: Int) {
            val options = Bundle()

            val intent = Intent(activity, SearchActivity::class.java)
            activity.startActivityForResult(intent, requestCode)
        }
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            recyclerView.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE

        }
    }

    override fun showMessage(message: String, error: Boolean) {
    }

    override fun showMessage(resId: Int, error: Boolean) {
    }

    override fun showEmpty() {
    }

    override fun showMessageView(show: Boolean) {
    }

    override fun showEntries(entries: List<Entry>) {
        recyclerView.visibility = View.VISIBLE
        searchAdapter.setItems(entries)
        searchAdapter.notifyDataSetChanged()
    }

    fun hideKeyboard() {
        if (searchView.hasFocus()) {
            searchView.let { v ->
                imm?.let { it.hideSoftInputFromWindow(v.windowToken, 0) }
            }
        }
    }

    override fun onClickEntry(entry: Entry) {
        Snackbar.make(fab, "Go to Profile", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }
}
