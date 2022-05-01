package ir.moonify.android.githubusers

import ir.moonify.android.githubusers.data.UsersDataSource
import ir.moonify.android.githubusers.domain.UserDetail
import ir.moonify.android.githubusers.domain.UserList
import ir.moonify.android.githubusers.framework.network.Services

class TestRemoteUserDataSource constructor(private val retrofitService: Services) :
    UsersDataSource {

    override suspend fun search(query: String): UserList {
        return retrofitService.searchUser("search/users", query)
    }

    override suspend fun get(username: String): UserDetail {
        return retrofitService.getUser("users/" + username)
    }
}