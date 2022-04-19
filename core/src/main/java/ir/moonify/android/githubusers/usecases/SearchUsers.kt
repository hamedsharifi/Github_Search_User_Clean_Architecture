package ir.moonify.android.githubusers.usecases

import ir.moonify.android.githubusers.data.UsersRepository

class SearchUsers(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(query: String) =
        usersRepository.searchUsers(query)
}