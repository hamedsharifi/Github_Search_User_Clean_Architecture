package ir.moonify.android.githubusers.data

import ir.moonify.android.githubusers.domain.UserDetail
import ir.moonify.android.githubusers.domain.UserList


interface UsersDataSource {
    suspend fun search(query: String): UserList
    suspend fun get(username: String): UserDetail
}