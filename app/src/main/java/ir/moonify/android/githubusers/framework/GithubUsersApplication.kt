package ir.moonify.android.githubusers.framework

import android.app.Application
import ir.moonify.android.githubusers.data.UsersRepository
import ir.moonify.android.githubusers.framework.network.RemoteUserDataSource
import ir.moonify.android.githubusers.framework.network.Services
import ir.moonify.android.githubusers.usecases.GetUserDetail
import ir.moonify.android.githubusers.usecases.SearchUser

class GithubUsersApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val usersRepository = UsersRepository(RemoteUserDataSource(Services.getInstance()))
        BaseViewModelFactory.inject(
            this,
            UseCases(
                SearchUser(usersRepository),
                GetUserDetail(usersRepository)
            )
        )
    }
}