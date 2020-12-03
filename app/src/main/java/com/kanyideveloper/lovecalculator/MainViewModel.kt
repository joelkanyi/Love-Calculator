package com.kanyideveloper.lovecalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainViewModel(private var fname: String, private var sname: String)  : ViewModel() {

    private val repository = MainRepository()

    var data : LiveData<LoveResults>

    init {
        this.data = repository.data
    }

    fun getDatas() {
        repository.getData(fname, sname)
    }
}
