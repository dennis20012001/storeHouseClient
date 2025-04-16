package com.example.storehouseclient.ui

import Users
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.storehouseclient.R
import com.example.storehouseclient.adapters.UserAdapter
import com.example.storehouseclient.endpoints.UserApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UsersFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter
    private var userList: List<Users> = mutableListOf()

    val BASE_URL = "http://10.0.2.2:8080/"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_users, container, false)

        recyclerView = rootView.findViewById(R.id.recyclerView)

        val layoutManager = GridLayoutManager(context, 3, RecyclerView.HORIZONTAL, false)

        recyclerView.layoutManager = layoutManager

        userAdapter = UserAdapter(emptyList())
        recyclerView.adapter = userAdapter

        fetchUsersFromApi()

        return rootView
    }

    private fun fetchUsersFromApi() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(UserApi::class.java)

        api.getUsers().enqueue(object : Callback<List<Users>> {
            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {
                if (response.isSuccessful) {
                    userList = (response.body() ?: emptyList()).filter { it.enabled }

                    for (user in userList) {
                        Log.d("API", "Usuario: ${user.name}, Imagen: ${user.image}")
                    }

                    userAdapter = UserAdapter(userList)
                    recyclerView.adapter = userAdapter
                } else {
                    Log.e("API", "Error en la respuesta: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                Log.e("API", "Error en la conexión: ${t.message}")
            }
        })

    }
}
