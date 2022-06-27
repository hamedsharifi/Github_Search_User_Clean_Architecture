package ir.moonify.android.githubusers.usecases

import ir.moonify.android.githubusers.data.UsersRepository

class SearchUserUseCase(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(query: String) =
        usersRepository.searchUsers(query)
}