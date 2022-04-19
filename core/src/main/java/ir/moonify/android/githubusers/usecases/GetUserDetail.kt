package ir.moonify.android.githubusers.usecases

import ir.moonify.android.githubusers.data.UsersRepository

class GetUserDetail(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(userName: String) =
        usersRepository.getUserDetail(userName)
}