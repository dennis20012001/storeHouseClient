package com.example.storehouseclient.adapters

import Users
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.storehouseclient.R
import com.example.storehouseclient.ui.ProductsFragment

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

        val imageUrl = user.image?.takeIf { it.isNotEmpty() }
        Glide.with(holder.profileImage.context)
            .load(imageUrl)
            .circleCrop()
            .placeholder(R.drawable.usuario)
            .error(R.drawable.usuario)
            .into(holder.profileImage)

        holder.profileImage.setOnClickListener {
            val context = holder.itemView.context

            val prefs = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
            prefs.edit().putString("jwt_token", user.pass).apply()

            (context as? AppCompatActivity)?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.fragment_container, ProductsFragment())
                ?.addToBackStack(null)
                ?.commit()
        }
    }

    override fun getItemCount(): Int = userList.size
}
