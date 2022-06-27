package ir.moonify.android.githubusers.usecases

import ir.moonify.android.githubusers.data.UsersRepository

class GetUserDetailUseCase(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(userName: String) =
        usersRepository.getUserDetail(userName)
}