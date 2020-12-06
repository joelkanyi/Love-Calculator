package com.kanyideveloper.lovecalculator

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainRepository {

    private val TAG = "MainRepository"

    val data = MutableLiveData<String>()
    val progressb = MutableLiveData<Boolean>()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    fun getData(fn:String, sn: String) {

        progressb.value = true

        val service = retrofit.create(RestAPI::class.java)

        val call: Call<LoveResults> = service.getLoversResult(fn, sn)

        call.enqueue(object : Callback<LoveResults> {
            override fun onFailure(call: Call<LoveResults>, t: Throwable) {
                progressb.value = false
                Log.d(TAG, "onFailure: ")
            }

            override fun onResponse(call: Call<LoveResults>, response: Response<LoveResults>) {
                progressb.value = false
                data.value = response.body().toString()
                Log.d(TAG, data.value.toString())
            }
        })
    }
}