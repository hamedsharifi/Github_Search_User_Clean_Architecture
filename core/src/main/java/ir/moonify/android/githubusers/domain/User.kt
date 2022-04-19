package ir.moonify.android.githubusers.domain

import java.io.Serializable

data class User(

    var login: String? = null,
    var id: Int? = null,
    var nodeId: String? = null,
    var avatarUrl: String? = null,
    var gravatarId: String? = null,
    var url: String? = null,
    var htmlUrl: String? = null,
    var followersUrl: String? = null,
    var subscriptionsUrl: String? = null,
    var organizationsUrl: String? = null,
    var reposUrl: String? = null,
    var receivedEventsUrl: String? = null,
    var type: String? = null,
    var score: Int? = null,
    var followingUrl: String? = null,
    var gistsUrl: String? = null,
    var starredUrl: String? = null,
    var eventsUrl: String? = null,
    var siteAdmin: Boolean? = null

)/*: Serializable {
    companion object {
        val EMPTY = User()
    }
}*/