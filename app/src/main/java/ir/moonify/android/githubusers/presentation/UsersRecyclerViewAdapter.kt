package ir.moonify.android.githubusers.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moonify.android.githubusers.R
import android.content.Intent
import android.widget.RelativeLayout
import androidx.cardview.widget.CardView


import ir.moonify.android.githubusers.domain.User
import ir.moonify.android.githubusers.util.Constants

internal class UsersRecyclerViewAdapter(
    private val context: Context,
    private val mList: List<User>
) : RecyclerView.Adapter<UsersRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_item, parent, false)
        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = mList[position]
        Glide.with(context).load(user.avatarUrl).into(holder.avatar)
        holder.userLogin.text = user.login
        holder.score.text = (context.getString(R.string.score_is, user.score.toString()))
        holder.item.setOnClickListener {
            val intent = Intent(context, UserDetailActivity::class.java).apply {
                putExtra(Constants.USER, user.login)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val item: RelativeLayout = itemView.findViewById(R.id.contentContainerRl)
        val avatar: ImageView = itemView.findViewById(R.id.profileImageIv)
        val userLogin: TextView = itemView.findViewById(R.id.usernameTv)
        val score: TextView = itemView.findViewById(R.id.scoreTv)
    }
}