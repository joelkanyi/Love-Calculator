package com.kanyideveloper.lovecalculator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainViewModel(private var fname: String, private var sname: String)  : ViewModel() {

    val data = MutableLiveData<String>()
    val progressb = MutableLiveData<Boolean>()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    fun getData() {

        progressb.value = true

        val service = retrofit.create(RestAPI::class.java)

        val call: Call<LoveResults> = service.getLoversResult(fname, sname)

        call.enqueue(object : Callback<LoveResults> {
            override fun onFailure(call: Call<LoveResults>, t: Throwable) {
                progressb.value = false
            }

            override fun onResponse(call: Call<LoveResults>, response: Response<LoveResults>) {
                progressb.value = false
                data.value = response.body().toString()
            }
        })
    }
}
