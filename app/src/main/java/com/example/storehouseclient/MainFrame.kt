package com.example.storehouseclient

import Users
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFrame : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val api = RetrofitClient.instance

        api.getUsers().enqueue(object : Callback<List<Users>> {
            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {
                if (response.isSuccessful) {
                    val users = response.body()
                    Log.d("Retrofit", "Usuarios: $users")
                } else {
                    Log.e("Retrofit", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                Log.e("Retrofit", "Error en la conexión: ${t.message}")
            }
        })
    }
}