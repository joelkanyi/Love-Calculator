package com.kanyideveloper.lovecalculator.ui

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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

        if (!isConnected) {
            showCustomDialog()
        }

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
            binding.percentage.text = "${it.percentage}%"
            binding.circleProgress.apply {
                progressMax = 100f
                setProgressWithAnimation(it.percentage.toFloat(), 1000)
                progressBarWidth = 15f
            }
        })

        viewModel.connectionMessage.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            binding.animationView.visibility = VISIBLE
            binding.circleProgress.visibility = INVISIBLE
            binding.percentage.visibility = INVISIBLE
            binding.loveMessage.visibility = INVISIBLE
            binding.progressBar.visibility = INVISIBLE
            binding.fname.visibility = INVISIBLE
            binding.lname.visibility = INVISIBLE
            binding.textView.visibility = INVISIBLE
            binding.calculate.visibility = INVISIBLE
        })

        viewModel.emptyStrings.observe(this, {
            if (it){
                Toast.makeText(this, "Please fill in both names", Toast.LENGTH_LONG).show()
                binding.animationView.visibility = INVISIBLE
                binding.circleProgress.visibility = INVISIBLE
                binding.percentage.visibility = INVISIBLE
                binding.loveMessage.visibility = INVISIBLE
                binding.progressBar.visibility = INVISIBLE
            }
        })
    }

    private fun View.hideKeyboard() {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun showCustomDialog() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setMessage("It seems you are not connect to the Internet, Please turn on your WIFI or Mobile Data and Try Again")
        alertDialogBuilder.setCancelable(false)
        alertDialogBuilder.setPositiveButton("Ok") { _, _ -> finish() }
        alertDialogBuilder.show()
    }

    private val Context.isConnected: Boolean
        get() {
            return (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo?.isConnected == true
        }
}
