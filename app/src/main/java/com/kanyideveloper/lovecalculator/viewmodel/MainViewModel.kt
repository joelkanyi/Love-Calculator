package com.kanyideveloper.lovecalculator.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kanyideveloper.lovecalculator.data.model.LoveResults
import com.kanyideveloper.lovecalculator.repository.MainRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import timber.log.Timber

class MainViewModel : ViewModel() {

    var fname: String? = null
    var sname: String? = null
    private val repository = MainRepository()
    private val timeout = 20000L

    val results: LiveData<LoveResults>
    val progressb = MutableLiveData<Boolean>()
    val connectionMessage = MutableLiveData<String>()
    val emptyStrings = MutableLiveData<Boolean>()

    init {
        this.results = repository.results
        emptyStrings.value = false
    }

    fun getResults(view: View) {

        progressb.value = true

        if (fname.isNullOrEmpty() && sname.isNullOrEmpty()) {
            Timber.d("Empty Strings")
            progressb.value = false
            emptyStrings.value = true
        }
        else {
            viewModelScope.launch {
                Timber.d("Making the network call")

                val job = withTimeoutOrNull(timeout) {
                    repository.getResults(fname.toString(), sname.toString())
                    progressb.value = false
                }
                if (job == null) {
                    Timber.d("Connection Timeout")
                    progressb.value = false
                    connectionMessage.value = "Connection Timeout, Please Try Again"
                }
            }
        }
    }
}