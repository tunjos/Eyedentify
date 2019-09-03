package eyedentify.email.di

import eyedentify.email.repo.GravatarRepository
import eyedentify.email.repo.GravatarRepositoryImpl
import org.koin.dsl.module.module

val dataModule = module {
    single<GravatarRepository> { GravatarRepositoryImpl(get()) }
}
