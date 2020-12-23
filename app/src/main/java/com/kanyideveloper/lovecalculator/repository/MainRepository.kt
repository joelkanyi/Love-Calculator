package com.kanyideveloper.lovecalculator.repository

import androidx.lifecycle.MutableLiveData
import com.kanyideveloper.lovecalculator.data.model.LoveResults
import com.kanyideveloper.lovecalculator.data.network.RetrofitBuilder
import timber.log.Timber

class MainRepository {

    var results = MutableLiveData<LoveResults>()
    val progressb = MutableLiveData<Boolean>()

    suspend fun getRes(fname: String, sname: String) {
        progressb.value = true
        results.value = RetrofitBuilder.apiService.getLoversResult(fname, sname)
        Timber.d(results.value.toString())
        progressb.value = false
    }
}