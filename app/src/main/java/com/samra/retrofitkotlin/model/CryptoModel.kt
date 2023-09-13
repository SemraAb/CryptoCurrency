package com.samra.retrofitkotlin.model

import com.google.gson.annotations.SerializedName

data class CryptoModel(
    @SerializedName("name")
    var currency :String ,
    var price: String) {
}