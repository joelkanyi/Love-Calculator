package com.kanyideveloper.lovecalculator

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {


    private  val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val first : TextView = findViewById(R.id.fname)
        val last : TextView = findViewById(R.id.lname)
        val body : TextView = findViewById(R.id.love_message)
        val calc : Button = findViewById(R.id.calculate)

        calc.setOnClickListener {

            val mainViewModelFactory = MainViewModelFactory(first.text.toString(), last.text.toString())
            val viewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)
            viewModel.getDatas()
            Toast.makeText(this, viewModel.data.value.toString(),Toast.LENGTH_LONG).show()
            body.text = viewModel.data.value.toString()
        }
    }
}