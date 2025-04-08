package com.example.storehouseclient.adapters

import Users
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.storehouseclient.R

class UserAdapter(private val userList: List<Users>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileImage: ImageView = itemView.findViewById(R.id.profile_image)
        val profileName: TextView = itemView.findViewById(R.id.profile_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_profile, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.profileName.text = user.name

        Glide.with(holder.profileImage.context)
            .load(user.image)
            .circleCrop()
            .placeholder(R.drawable.usuario)
            .into(holder.profileImage)

    }

    override fun getItemCount(): Int = userList.size
}
