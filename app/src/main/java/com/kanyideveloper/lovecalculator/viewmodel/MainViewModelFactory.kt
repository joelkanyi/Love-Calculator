package com.kanyideveloper.lovecalculator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kanyideveloper.lovecalculator.data.network.ApiService
import com.kanyideveloper.lovecalculator.repository.MainRepository

class MainViewModelFactory(private val apiService: ApiService) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainRepository::class.java)){
            return MainViewModel(apiService) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}