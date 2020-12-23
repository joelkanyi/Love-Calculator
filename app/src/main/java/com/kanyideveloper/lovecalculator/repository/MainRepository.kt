package com.kanyideveloper.lovecalculator.repository

import androidx.lifecycle.MutableLiveData
import com.kanyideveloper.lovecalculator.data.model.LoveResults
import com.kanyideveloper.lovecalculator.data.network.RetrofitBuilder

class MainRepository{

    private val TAG = "MainRepository"

    var results = MutableLiveData<LoveResults>()

    val progressb = MutableLiveData<Boolean>()


    suspend fun getRes(fname: String, sname: String){

       results.value = RetrofitBuilder.apiService.getLoversResult(fname,sname)

    }
}