package com.rmb.bootcampgrad.data.repo

import com.rmb.bootcampgrad.data.datasource.ProductsDataSource
import com.rmb.bootcampgrad.data.entity.CRUDResponse
import com.rmb.bootcampgrad.data.entity.Products
import com.rmb.bootcampgrad.data.entity.ProductsMyBasket

class ProductsRepository(var productsDataSource : ProductsDataSource) {

    suspend fun deleteFromBasket(id: Int) = productsDataSource.deleteFromBasket(id)

    suspend fun loadProducts() : List<Products> = productsDataSource.loadProducts()

    suspend fun loadBasketProducts() : List<ProductsMyBasket>? = productsDataSource.loadBasketProducts()

    suspend fun search(searchText: String) : List<Products> = productsDataSource.search(searchText)

    suspend fun addToBasket(ad: String,
                            resim: String,
                            kategori: String,
                            fiyat: Int,
                            marka: String,
                            siparisAdeti: Int) : CRUDResponse = productsDataSource.addToBasket(ad,resim,kategori,fiyat,marka,siparisAdeti)


}