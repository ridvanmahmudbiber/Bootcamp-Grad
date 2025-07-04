package com.rmb.bootcampgrad.data.entity

import java.io.Serializable

data class Products(
    val id: Int,
    //@SerializedName("ad")
    val name: String,
    //@SerializedName("resim")
    val image: String,
    // @SerializedName("kategori")
    val category: String,
    // @SerializedName("fiyat")
    val price: Int,
    // @SerializedName("marka")
    val brand: String
) : Serializable {
}
