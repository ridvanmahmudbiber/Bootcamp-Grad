package com.rmb.bootcampgrad.data.repo

import com.rmb.bootcampgrad.data.datasource.ProductsDataSource
import com.rmb.bootcampgrad.data.entity.Products

class ProductsRepository(var productsDataSource : ProductsDataSource) {

    suspend fun delete(id: Int) = productsDataSource.delete(id)

    suspend fun loadProducts() : List<Products> = productsDataSource.loadProducts()

    suspend fun loadBasketProducts() : List<Products> = productsDataSource.loadBasketProducts()

    suspend fun search(searchText: String) : List<Products> = productsDataSource.search(searchText)

}