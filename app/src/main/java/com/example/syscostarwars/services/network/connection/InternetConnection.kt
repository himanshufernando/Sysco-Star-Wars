package com.example.syscostarwars.services.network.connection
import android.content.Context
import android.net.ConnectivityManager
import com.example.syscostarwars.StarWars


object InternetConnection {
    var app : Context = StarWars.applicationContext()
    fun checkInternetConnection(): Boolean {
        var isConnexted = false
        val connect = app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connect.activeNetworkInfo
        if (activeNetwork != null) {
            if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
                isConnexted = true
            } else if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
                isConnexted = true
            }
        } else {
            isConnexted = false
        }
        return isConnexted
    }
}