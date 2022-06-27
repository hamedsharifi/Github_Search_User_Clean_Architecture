package ir.moonify.android.githubusers.framework

import ir.moonify.android.githubusers.usecases.GetUserDetailUseCase
import ir.moonify.android.githubusers.usecases.SearchUserUseCase

data class UseCases(
    val searchUserUseCase: SearchUserUseCase,
    val getUserDetailUseCase: GetUserDetailUseCase
)