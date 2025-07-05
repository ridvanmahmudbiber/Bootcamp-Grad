package com.rmb.bootcampgrad.retrofit

import com.rmb.bootcampgrad.core.APIPaths
import com.rmb.bootcampgrad.core.Constants
import com.rmb.bootcampgrad.data.entity.CRUDResponse
import com.rmb.bootcampgrad.data.entity.Products
import com.rmb.bootcampgrad.data.entity.ProductsMyBasketResponse
import com.rmb.bootcampgrad.data.entity.ProductsResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ProductsDao {

    @GET(APIPaths.getProducts)
    suspend fun getProducts(): ProductsResponse

    @POST(APIPaths.addToBasket)
    @FormUrlEncoded
    suspend fun addToBasket(
        @Field("ad") ad: String,
        @Field("resim") resim: String,
        @Field("kategori") kategori: String,
        @Field("fiyat")fiyat: Int,
        @Field("marka")marka: String,
        @Field("siparisAdeti") siparisAdeti: Int,
        @Field("kullaniciAdi") kullaniciAdi: String
    ):  CRUDResponse

    @POST(APIPaths.getMyBasket)
    @FormUrlEncoded
    suspend fun getMyBasket(@Field("kullaniciAdi") kullaniciAdi: String = Constants.username): ProductsMyBasketResponse

    @POST(APIPaths.deleteFromBasket)
    @FormUrlEncoded
    suspend fun deleteFromBasket(
        @Field("sepetId") sepetId: Int,
        @Field("kullaniciAdi") kullaniciAdi: String = Constants.username
    ): CRUDResponse


}