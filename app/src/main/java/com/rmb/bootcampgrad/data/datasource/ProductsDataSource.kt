package com.rmb.bootcampgrad.data.datasource

import com.rmb.bootcampgrad.core.Constants
import com.rmb.bootcampgrad.data.entity.CRUDResponse
import com.rmb.bootcampgrad.data.entity.Products
import com.rmb.bootcampgrad.data.entity.ProductsMyBasket
import com.rmb.bootcampgrad.retrofit.ProductsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductsDataSource(var productsDao: ProductsDao) {

    suspend fun loadProducts() : List<Products> = withContext(Dispatchers.IO){

        return@withContext productsDao.getProducts().urunler
    }

    suspend fun loadBasketProducts() : List<ProductsMyBasket>? = withContext(Dispatchers.IO){

        return@withContext productsDao.getMyBasket(Constants.username).urunler_sepeti
    }

    suspend fun addToBasket(ad: String,
                            resim: String,
                            kategori: String,
                            fiyat: Int,
                            marka: String,
                            siparisAdeti: Int,
                            kullaniciAdi: String = Constants.username)  : CRUDResponse{
        return productsDao.addToBasket(ad,resim,kategori,fiyat,marka,siparisAdeti,kullaniciAdi)
    }

    suspend fun deleteFromBasket(sepetId: Int) : CRUDResponse{
        return productsDao.deleteFromBasket(sepetId)
    }

    suspend fun search(searchText: String) : List<Products> = withContext(Dispatchers.IO){
        val productsList = ArrayList<Products>()
        val p1 = Products(1,"Saat", "yildiz","Saat",100,"Rolex")
        productsList.add(p1)

        return@withContext productsList
    }
}