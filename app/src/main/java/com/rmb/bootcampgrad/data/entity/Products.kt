package com.rmb.bootcampgrad.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Products(
    val id: Int,
    val ad: String,
    val resim: String,
    val kategori: String,
    val fiyat: Int,
    val marka: String
) : Serializable
