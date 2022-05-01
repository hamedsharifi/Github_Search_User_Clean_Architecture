package ir.moonify.android.githubusers.domain

import com.google.gson.annotations.SerializedName

data class UserList(
    @SerializedName("total_count"        ) var totalCount        : Int?             = null,
    @SerializedName("incomplete_results" ) var incompleteResults : Boolean?         = null,
    @SerializedName("items"              ) var items             : ArrayList<User> = arrayListOf()

)
