package com.samra.retrofitkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.samra.retrofitkotlin.R
import com.samra.retrofitkotlin.adapter.CryptoAdapter
import com.samra.retrofitkotlin.databinding.ActivityMainBinding
import com.samra.retrofitkotlin.model.CryptoModel
import com.samra.retrofitkotlin.model.DataModel
import com.samra.retrofitkotlin.service.CryptoApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() , CryptoAdapter.Listener {
private lateinit var binding:ActivityMainBinding
    private val BASE_URL = "https://pro-api.coinmarketcap.com/v1/"
    private var cryptoModels:CryptoModel? = null
    private lateinit var recyclerAdapter:CryptoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadData()
        var layoutManager : RecyclerView.LayoutManager=LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

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
        call.enqueue(object: Callback<CryptoModel>{
            override fun onResponse(
                call: Call<CryptoModel>,
                response: Response<CryptoModel>
            ) {
                if(response.isSuccessful){
                        recyclerAdapter = CryptoAdapter(response.body()!! ,this@MainActivity )
                        binding.recyclerView.adapter = recyclerAdapter

                }
            }

            override fun onFailure(call: Call<CryptoModel>, t: Throwable) {
               t.printStackTrace()
            }

        })
    }

    override fun onItemCLicked(cryptoModel: DataModel) {
        Toast.makeText(this , "CLicked: ${cryptoModel.name}" , Toast.LENGTH_LONG).show()
    }
}