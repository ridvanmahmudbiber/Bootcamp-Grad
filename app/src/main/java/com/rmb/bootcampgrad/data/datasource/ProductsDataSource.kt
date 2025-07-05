package com.rmb.bootcampgrad.data.datasource

import com.rmb.bootcampgrad.core.Constants
import com.rmb.bootcampgrad.data.entity.CRUDResponse
import com.rmb.bootcampgrad.data.entity.Products
import com.rmb.bootcampgrad.data.entity.ProductsMyBasket
import com.rmb.bootcampgrad.retrofit.ProductsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductsDataSource(var productsDao: ProductsDao) {

    /* suspend fun loadProducts(): List<Products> = withContext(Dispatchers.IO) {

         return@withContext productsDao.getProducts().urunler
     }

     */

    suspend fun loadProducts(category: String?): List<Products> = withContext(Dispatchers.IO) {
        val allProducts = productsDao.getProducts().urunler
        println(allProducts)
        allProducts.forEach {
            println("Ürün: ${it.ad} | Kategori: '${it.kategori}'")
        }
        return@withContext if (category.isNullOrBlank()) {
            allProducts
        } else {
            allProducts.filter { it.kategori == category }

        }
    }

    suspend fun loadBasketProducts(): List<ProductsMyBasket>? = withContext(Dispatchers.IO) {

        return@withContext productsDao.getMyBasket(Constants.username).urunler_sepeti
    }

    suspend fun addToBasket(
        ad: String,
        resim: String,
        kategori: String,
        fiyat: Int,
        marka: String,
        siparisAdeti: Int,
        kullaniciAdi: String = Constants.username
    ): CRUDResponse {
        return productsDao.addToBasket(
            ad,
            resim,
            kategori,
            fiyat,
            marka,
            siparisAdeti,
            kullaniciAdi
        )
    }

    suspend fun deleteFromBasket(sepetId: Int): CRUDResponse {
        return productsDao.deleteFromBasket(sepetId)
    }

    suspend fun search(searchText: String): List<Products> = withContext(Dispatchers.IO) {
        val currentList = productsDao.getProducts().urunler
        println(currentList)
        return@withContext if (searchText.isBlank()) {
            currentList
        } else {
            currentList.filter {
                it.ad.contains(searchText, ignoreCase = true) ||
                        it.marka.contains(searchText, ignoreCase = true)
            }
        }

    }
}