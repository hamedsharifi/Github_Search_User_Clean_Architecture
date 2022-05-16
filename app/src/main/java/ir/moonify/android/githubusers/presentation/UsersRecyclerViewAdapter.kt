package ir.moonify.android.githubusers.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moonify.android.githubusers.R

import ir.moonify.android.githubusers.domain.User

internal class UsersRecyclerViewAdapter(private val mList: List<User>) : RecyclerView.Adapter<UsersRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_item, parent, false)
        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = mList[position]
        Glide.with(holder.avatar.context).load(user.avatarUrl).into(holder.avatar)
        holder.userLogin.text = user.login
        holder.score.text = (holder.avatar.context.getString(R.string.score_is, user.score.toString()))
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val avatar: ImageView = itemView.findViewById(R.id.profileImageIv)
        val userLogin: TextView = itemView.findViewById(R.id.usernameTv)
        val score: TextView = itemView.findViewById(R.id.scoreTv)
    }
}