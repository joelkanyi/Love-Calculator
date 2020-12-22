package com.kanyideveloper.lovecalculator

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


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


    fun getRes(fname: String, sname: String) = liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = apiService.getLoversResult(fname,sname)))
            }catch (exception: Exception){
                emit(Resource.error(data=null,message = exception.message?:"Error occurred"))
            }
        }
}