package com.rmb.bootcampgrad.retrofit

class ApiUtils {
    companion object{
        val baseUrl = "http://kasimadalan.pe.hu/"

        fun getProductsDao(): ProductsDao {
            return RetrofitClient.getClient(baseUrl).create(ProductsDao::class.java)
        }

    }
}