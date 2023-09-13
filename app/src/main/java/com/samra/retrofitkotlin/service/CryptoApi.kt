package com.samra.retrofitkotlin.service

import com.samra.retrofitkotlin.model.CryptoModel
import retrofit2.Call
import retrofit2.http.GET

interface CryptoApi {
    //https://pro-api.coinmarketcap.com/v1/
    // cryptocurrency/listings/latest?CMC_PRO_API_KEY=8ee21f53-aa6b-4d50-853e-5725d7c44334
@GET("https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/")
fun getData(): Call<List<CryptoModel>>

}