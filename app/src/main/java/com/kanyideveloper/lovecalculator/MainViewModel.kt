package com.kanyideveloper.lovecalculator

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


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


    fun getRes(view: View) {
           if (fname.isNullOrEmpty() && sname.isNullOrEmpty()){
               Log.d(TAG, "getRes: enoty strings")
           }

        viewModelScope.launch{
            repository.getRes(fname.toString(),sname.toString())
        }
    }
}