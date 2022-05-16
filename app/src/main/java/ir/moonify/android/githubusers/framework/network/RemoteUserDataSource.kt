package ir.moonify.android.githubusers.framework.network

import ir.moonify.android.githubusers.data.UsersDataSource
import ir.moonify.android.githubusers.domain.UserDetail
import ir.moonify.android.githubusers.domain.UserList

class RemoteUserDataSource constructor(private val retrofitService: Services) : UsersDataSource {

    companion object {
        init {
            System.loadLibrary("native-lib")
        }
    }

    override suspend fun search(query: String): UserList {
        return retrofitService.searchUser(getSearchUrl(), query)
    }

    override suspend fun get(username: String): UserDetail {
        return retrofitService.getUser(getUserUrl() + username)
    }


    // Hiding API URLs
    external fun getSearchUrl(): String
    external fun getUserUrl(): String
}