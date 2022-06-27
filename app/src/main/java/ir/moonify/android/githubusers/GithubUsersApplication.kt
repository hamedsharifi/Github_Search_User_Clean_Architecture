package ir.moonify.android.githubusers

import android.app.Application
import ir.moonify.android.githubusers.di.getUserDetailModule
import ir.moonify.android.githubusers.di.searchModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GithubUsersApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GithubUsersApplication)
            modules(
                listOf(
                    searchModule,
                    getUserDetailModule
                )
            )
        }
    }
}