package com.kanyideveloper.lovecalculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

        val service = retrofit.create(RestAPI::class.java)
        val call : Call<Results> = service.getLoversResult("Joel","Beatrice")

        call.enqueue(object : Callback<Results>{
            override fun onFailure(call: Call<Results>, t: Throwable) {

            }

            override fun onResponse(call: Call<Results>, response: Response<Results>) {
                Toast.makeText(this@MainActivity,response.body().toString(),Toast.LENGTH_LONG).show()
            }

        })

    }
}