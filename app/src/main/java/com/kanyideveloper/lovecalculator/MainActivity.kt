package com.kanyideveloper.lovecalculator

import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kanyideveloper.lovecalculator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {


    private  val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModelFactory: MainViewModelFactory
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.calculate.setOnClickListener {

           mainViewModelFactory = MainViewModelFactory(binding.fname.text.toString(), binding.lname.text.toString())
            viewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)

            viewModel.getData()

            viewModel.progressb.observe(this, Observer {
                if (it){
                    binding.progressBar.visibility = VISIBLE
                }
                else{
                    binding.progressBar.visibility = INVISIBLE
                }
            })

            viewModel.data.observe(this, Observer {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                binding.loveMessage.text = it
            })
        }
    }
}