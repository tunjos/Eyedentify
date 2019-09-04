package eyedentify.email.search.presenters

import android.util.Log
import eyedentify.email.api.GravatarApi
import eyedentify.email.repo.GravatarRepository
import eyedentify.email.search.views.SearchView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class SearchPresenter(private val gravatarRepository: GravatarRepository) {
    private var searchView: SearchView? = null
    private val compositeDisposable = CompositeDisposable()

    fun getProfile(email: String) {
        searchView?.showProgress(true)
        val disposable = gravatarRepository.getProfile(email)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                val emailEntries = it.entry?.map { it ->
                    it.email = email
                    return@map it
                }
                emailEntries
            }
            .subscribe({
                it?.let {
                    searchView?.showProgress(false)
                    searchView?.showEntries(it)
                    Log.d("ED", it.toString())
                }
            }, {
                if (it is HttpException) {
                    if (it.code() == GravatarApi.HTTP_404) {

                    }
                }
                Log.d("ED", it.message)
                searchView?.showProgress(false)
            })
        compositeDisposable.add(disposable)
    }

    fun attachView(searchView: SearchView) {
        this.searchView = searchView
    }

    fun detachView() {
        this.searchView = null
        compositeDisposable.dispose()
    }
}
