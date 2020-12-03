package com.kanyideveloper.lovecalculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class MainViewModelFactory(private var firstName : String, private var secondName : String) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(firstName,secondName) as T
        }
        throw IllegalArgumentException("ViewModel class not found")
    }
}