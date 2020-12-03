package com.kanyideveloper.lovecalculator

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
 class MainRepository{

     private  val TAG = "MainRepository"

     val data = MutableLiveData<LoveResults>()

     fun getData(fn : String, sn: String){
         val retrofit = Retrofit.Builder()
                 .addConverterFactory(GsonConverterFactory.create())
                 .baseUrl(BASE_URL)
                 .build()

         val service = retrofit.create(RestAPI::class.java)

         val call : Call<LoveResults> = service.getLoversResult(fn,sn)

         call.enqueue(object : Callback<LoveResults> {
             override fun onFailure(call: Call<LoveResults>, t: Throwable) {
                 Log.d(TAG, "onFailure: ")
             }

             override fun onResponse(call: Call<LoveResults>, response: Response<LoveResults>) {
                 data.value = response.body()
                 Log.d(TAG, data.value?.fname.toString())
             }
         })
     }
 }