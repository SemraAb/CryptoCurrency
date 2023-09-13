package com.samra.retrofitkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.samra.retrofitkotlin.R
import com.samra.retrofitkotlin.model.CryptoModel
import com.samra.retrofitkotlin.service.CryptoApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val BASE_URL = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/"
    private var cryptoModels:ArrayList<CryptoModel>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadData()
    }
//    https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/
// latest?CMC_PRO_API_KEY=8ee21f53-aa6b-4d50-853e-5725d7c44334

    fun loadData(){
        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var service = retrofit.create(CryptoApi::class.java)
        var call = service.getData()
        call.enqueue(object: Callback<List<CryptoModel>>{
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {
                if(response.isSuccessful){
                    response.body()?.let{
                        cryptoModels = ArrayList(it)

                        for(cryptoModel: CryptoModel in cryptoModels!!){

                            println(cryptoModel)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
               t.printStackTrace()
            }

        })
    }
}