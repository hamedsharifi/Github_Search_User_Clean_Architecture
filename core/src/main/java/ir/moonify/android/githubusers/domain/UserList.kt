package ir.moonify.android.githubusers.domain

data class UserList(
    var totalCount: Int? = null,
    var incompleteResults: Boolean? = null,
    var items: ArrayList<User> = arrayListOf()
)
