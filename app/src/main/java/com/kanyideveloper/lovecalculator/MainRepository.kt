package com.kanyideveloper.lovecalculator

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainRepository(private val fname: String, private val lname: String){

    private  val TAG = "Repository"

    private val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

    fun getData(){
        val service = retrofit.create(RestAPI::class.java)

        val call : Call<List<LoveResults>> = service.getLoversResult(fname,lname)

        call.enqueue(object : Callback<List<LoveResults>>{
            override fun onFailure(call: Call<List<LoveResults>>, t: Throwable) {
                Log.d(TAG, "Failed")
            }

            override fun onResponse(call: Call<List<LoveResults>>, response: Response<List<LoveResults>>) {
                Log.d(TAG, response.message())
            }

        })
    }
}