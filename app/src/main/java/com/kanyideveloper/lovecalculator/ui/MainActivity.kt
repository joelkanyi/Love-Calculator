package com.kanyideveloper.lovecalculator.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.kanyideveloper.lovecalculator.R
import com.kanyideveloper.lovecalculator.databinding.ActivityMainBinding
import com.kanyideveloper.lovecalculator.viewmodel.MainViewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.d("onCreate")

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //To make marquee moving
        binding.textView.isSelected = true

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.viewmodel = viewModel

        viewModel.progressb.observe(this, {
            if (it) {
                binding.calculate.hideKeyboard()
                binding.progressBar.visibility = VISIBLE
                binding.circleProgress.visibility = INVISIBLE
                binding.percentage.visibility = INVISIBLE
                binding.loveMessage.visibility = INVISIBLE
            } else {
                binding.progressBar.visibility = INVISIBLE
                binding.circleProgress.visibility = VISIBLE
                binding.percentage.visibility = VISIBLE
                binding.loveMessage.visibility = VISIBLE
            }
        })

        viewModel.results.observe(this, {
            binding.loveMessage.text = it.result
            binding.circleProgress.apply {
                progressMax = 100f
                setProgressWithAnimation(it.percentage.toFloat(), 1000)
                progressBarWidth = 15f
                binding.percentage.text = "${it.percentage}%"
            }
        })
    }

    private fun View.hideKeyboard() {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }
}
