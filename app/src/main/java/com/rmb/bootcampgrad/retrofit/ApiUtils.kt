package com.rmb.bootcampgrad.retrofit

import com.rmb.bootcampgrad.core.APIPaths

class ApiUtils {
    companion object{
        fun getProductsDao(): ProductsDao {
            return RetrofitClient.getClient(APIPaths.baseUrl).create(ProductsDao::class.java)
        }
    }
}