package com.kanyideveloper.lovecalculator

import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainRepository{

    private val TAG = "MainRepository"

    var results = MutableLiveData<LoveResults>()

    val progressb = MutableLiveData<Boolean>()


    suspend fun getRes(fname: String, sname: String){

      //  progressb.value = true

        val retrofit  =  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RestAPI::class.java)

        results.value = service.getLoversResult(fname, sname)

       /* call.enqueue(object : Callback<LoveResults>{
            override fun onFailure(call: Call<LoveResults>, t: Throwable) {
                progressb.value = false
                Log.d(TAG, "onFailure: failed")
            }

            override fun onResponse(call: Call<LoveResults>, response: Response<LoveResults>) {
                progressb.value = false
                results.value = response.body()
                Log.d(TAG, "onResponse: ${response.body()}")
            }
        })*/
    }
}