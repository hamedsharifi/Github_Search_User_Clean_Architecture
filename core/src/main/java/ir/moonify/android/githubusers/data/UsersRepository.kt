package ir.moonify.android.githubusers.data

class UsersRepository(private val usersDataSource: UsersDataSource) {

    suspend fun searchUsers(query: String) = usersDataSource.search(query)

}