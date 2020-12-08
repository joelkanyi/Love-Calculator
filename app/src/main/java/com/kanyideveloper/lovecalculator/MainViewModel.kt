package com.kanyideveloper.lovecalculator

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel(){
    var fname: String? = null
    var sname: String? = null

    private val repository = MainRepository()

    val results : LiveData<LoveResults>
    val progressb : LiveData<Boolean>

    init {
        this.results = repository.results
        this.progressb = repository.progressb
    }
    private val TAG = "MainViewModel"


    fun getRes(view: View){
           if (fname.isNullOrEmpty() && sname.isNullOrEmpty()){
               Log.d(TAG, "getRes: enoty strings")
           }
        repository.getRes(fname.toString(),sname.toString())
    }

}