package com.samra.retrofitkotlin.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samra.retrofitkotlin.R
import com.samra.retrofitkotlin.databinding.RecyclerRowBinding
import com.samra.retrofitkotlin.model.CryptoModel
import com.samra.retrofitkotlin.model.DataModel


class CryptoAdapter(private var cryptoList: CryptoModel , private var listener:Listener): RecyclerView.Adapter<CryptoAdapter.CryptoHolder>() {
   interface Listener{
       fun onItemCLicked(cryptoModel: DataModel)
   }

    private var colors: Array<String> = arrayOf("#609669", "#b539c9" , "#f1c232" , "#1a8da6" , "#cc1212" , "#a16a14")
    class CryptoHolder(var binding: RecyclerRowBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(cryptoModel:DataModel , colors:Array<String>, position: Int , listener: Listener){
            itemView.setBackgroundColor(Color.parseColor(colors[position % 6]))
            itemView.setOnClickListener{
                listener.onItemCLicked(cryptoModel)
            }
            binding.cryptoName.text = cryptoModel.name
            binding.cryptoPrice.text = cryptoModel.quote.USD.price.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHolder {
        var binding  = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return CryptoHolder(binding)
    }

    override fun getItemCount(): Int {
        return cryptoList.data.size
    }

    override fun onBindViewHolder(holder: CryptoHolder, position: Int) {
       holder.bind(cryptoList.data[position] , colors, position , listener )
    }
}