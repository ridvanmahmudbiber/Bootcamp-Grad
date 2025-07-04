package com.rmb.bootcampgrad.data.datasource

import com.rmb.bootcampgrad.data.entity.Products
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductsDataSource {
    suspend fun delete(id: Int) {

    }

    suspend fun loadProducts() : List<Products> = withContext(Dispatchers.IO){
        val productsList = ArrayList<Products>()
        val p1 = Products(1,"Saat", "yildiz","Saat",100,"Rolex")
        val p2 = Products(2,"Çanta","yildiz","Saat",500,"Rolex")
        val p3 = Products(3,"Kalem","yildiz","Saat",250,"Rolex")
        productsList.add(p1)
        productsList.add(p2)
        productsList.add(p3)

        return@withContext productsList
    }

    suspend fun loadBasketProducts() : List<Products> = withContext(Dispatchers.IO){
        val productsList = ArrayList<Products>()
        val p1 = Products(1,"Saat", "yildiz","Saat",100,"Rolex")
        val p2 = Products(2,"Çanta","yildiz","Saat",500,"Rolex")
        val p3 = Products(3,"Kalem","yildiz","Saat",250,"Rolex")
        productsList.add(p1)
        productsList.add(p2)
        productsList.add(p3)

        return@withContext productsList
    }

    suspend fun search(searchText: String) : List<Products> = withContext(Dispatchers.IO){
        val productsList = ArrayList<Products>()
        val p1 = Products(1,"Saat", "yildiz","Saat",100,"Rolex")
        productsList.add(p1)

        return@withContext productsList
    }
}