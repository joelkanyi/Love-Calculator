package com.kanyideveloper.lovecalculator

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel(){
    var fname: String? = null
    var sname: String? = null

    private val TAG = "MainViewModel"

    fun getRes(view: View){

        val retrofit  =  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RestAPI::class.java)

        val call: Call<LoveResults> = service.getLoversResult(fname, sname)

        call.enqueue(object : Callback<LoveResults>{
            override fun onFailure(call: Call<LoveResults>, t: Throwable) {
               // Toast.makeText(this@MainActivity, "Failed", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "onFailure: failed")
            }

            override fun onResponse(call: Call<LoveResults>, response: Response<LoveResults>) {
                //Toast.makeText(this@MainActivity, response.body().toString(), Toast.LENGTH_SHORT).show()
                Log.d(TAG, "onResponse: ${response.body()}")
            }

        })
    }
}

/*
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
*/
