package com.kanyideveloper.lovecalculator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.kanyideveloper.lovecalculator.Utils.Resource
import com.kanyideveloper.lovecalculator.data.network.ApiService
import com.kanyideveloper.lovecalculator.data.model.LoveResults
import com.kanyideveloper.lovecalculator.repository.MainRepository
import kotlinx.coroutines.Dispatchers


class MainViewModel(private val apiService: ApiService) : ViewModel(){
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


    fun getResults(fname: String?, sname: String?) = liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = apiService.getLoversResult(fname, sname)))
            }catch (exception: Exception){
                emit(Resource.error(data = null, message = exception.message ?: "Error occurred"))
            }
        }
}