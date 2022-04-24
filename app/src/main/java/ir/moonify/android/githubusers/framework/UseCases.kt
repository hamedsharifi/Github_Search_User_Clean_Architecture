package ir.moonify.android.githubusers.framework

import ir.moonify.android.githubusers.usecases.GetUserDetail
import ir.moonify.android.githubusers.usecases.SearchUser

data class UseCases(
    val searchUser: SearchUser,
    val getUserDetail: GetUserDetail
)