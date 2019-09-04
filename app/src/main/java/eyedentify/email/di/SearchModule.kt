package eyedentify.email.di

import eyedentify.email.search.adapters.SearchAdapter
import eyedentify.email.search.presenters.SearchPresenter
import org.koin.dsl.module.module

val searchModule = module {
    factory { SearchPresenter(get()) }
    factory { SearchAdapter() }
}
