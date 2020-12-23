package com.kanyideveloper.lovecalculator.viewmodel

import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kanyideveloper.lovecalculator.data.model.LoveResults
import com.kanyideveloper.lovecalculator.repository.MainRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel : ViewModel() {

    var fname: String? = null
    var sname: String? = null
    private val repository = MainRepository()

    val results: LiveData<LoveResults>
    val progressb: LiveData<Boolean>

    init {
        this.results = repository.results
        this.progressb = repository.progressb
    }

    fun getResults(view: View) {

        if (fname.isNullOrEmpty() && sname.isNullOrEmpty()) {
            Timber.d("Empty Strings")
        } else {
            viewModelScope.launch {
                Timber.d("Making the network call")
                repository.getRes(fname.toString(), sname.toString())
            }
        }
    }
}