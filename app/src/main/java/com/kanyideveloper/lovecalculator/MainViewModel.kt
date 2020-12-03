package com.kanyideveloper.lovecalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel(){

    private val repository = MainRepository("john","joy")

    fun getData(){
        repository.getData()
    }
}
