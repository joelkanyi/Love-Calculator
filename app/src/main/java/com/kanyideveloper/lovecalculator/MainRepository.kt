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

       results.value = RetrofitBuilder.apiService.getLoversResult(fname,sname)

    }
}