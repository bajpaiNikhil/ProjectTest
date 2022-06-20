package com.example.projecttest.service

import android.annotation.SuppressLint
import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import com.thecode.aestheticdialogs.*


class CheckInternet:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        //isOnline(context!!)

//        val i = Intent(context , DialogActivity::class.java)
//        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//        context?.startActivity(i)


        Log.d("MainActivity" , "this is receiver , ${isOnline(context!!)}")
        if (isOnline(context!!)){
            Toast.makeText(context ,"InternetAvailable" , Toast.LENGTH_SHORT ).show()
        }else{
            Toast.makeText(context ,"InternetNotAvailable" , Toast.LENGTH_SHORT ).show()
        }

    }
    fun isOnline(context: Context): Boolean {
        val cMan = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nInfo = cMan.activeNetworkInfo
        Log.d("MainActivity" , "$nInfo  , ${nInfo?.isConnected}")
        return nInfo != null && nInfo.isConnected
    }

}