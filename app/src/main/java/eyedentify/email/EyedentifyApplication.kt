package eyedentify.email

import android.app.Application
import eyedentify.email.di.dataModule
import eyedentify.email.di.searchModule
import eyedentify.email.di.networkModule
import org.koin.android.ext.android.startKoin

class EyedentifyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(searchModule, networkModule, dataModule))

    }
}
