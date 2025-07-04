package com.rmb.bootcampgrad.data.entity

import java.io.Serializable

data class ProductsMyBasket(
    val sepetId: Int,
    val ad: String,
    val resim: String,
    val kategori: String,
    val fiyat: Int,
    val marka: String,
    val siparisAdeti: Int,
    val kullaniciAdi: String
) : Serializable {
}

