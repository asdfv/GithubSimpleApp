package by.grodno.vasili.githubsimpleapp

import android.app.Application
import timber.log.Timber
import timber.log.Timber.DebugTree

class GithubSimpleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}
