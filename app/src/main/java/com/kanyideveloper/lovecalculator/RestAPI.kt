package com.kanyideveloper.lovecalculator

import retrofit2.Call
import retrofit2.http.*


const val BASE_URL = "https://love-calculator.p.rapidapi.com/"

interface RestAPI {

    @Headers("x-rapidapi-key: 72ca1bbcd4msh2635007a217ffb8p140999jsndca5f4311295")
    @GET("getPercentage")
    suspend fun getLoversResult(@Query("fname") fName: String?, @Query("sname") sName: String?) : LoveResults
}
