package com.rmb.bootcampgrad.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmb.bootcampgrad.data.entity.Products
import com.rmb.bootcampgrad.data.entity.ProductsMyBasket
import com.rmb.bootcampgrad.data.repo.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.EOFException
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(var productsRepository : ProductsRepository) : ViewModel() {
    var basketProductsList = MutableLiveData<List<ProductsMyBasket>?>()

    init {
        loadBasketProducts()
    }

    fun loadBasketProducts() {
        viewModelScope.launch {
            try {
                val products = productsRepository.loadBasketProducts()
                val groupedProducts = products?.let { groupBasketProducts(it) }
                basketProductsList.value = groupedProducts
                println(basketProductsList.value)

            }catch (e: EOFException) {
                basketProductsList.value = null
            }
        }
    }

    fun groupBasketProducts(products: List<ProductsMyBasket>): List<ProductsMyBasket> {
        return products
            .groupBy { Triple(it.ad, it.marka, it.resim) }
            .map { (_, groupedList) ->
                val first = groupedList.first()
                first.copy(
                    siparisAdeti = groupedList.sumOf { it.siparisAdeti },
                )
            }
    }

    fun deleteAllSameProducts(ad: String, marka: String) {
        viewModelScope.launch {
            val currentList = productsRepository.loadBasketProducts()

            val sameProducts = currentList?.filter { it.ad == ad && it.marka == marka }

            sameProducts?.forEach {
                productsRepository.deleteFromBasket(it.sepetId)
            }

            loadBasketProducts()
        }
    }

    fun increaseQuantity(product: ProductsMyBasket) {
        viewModelScope.launch {
            productsRepository.addToBasket(
                ad = product.ad,
                fiyat = product.fiyat,
                resim = product.resim,
                kategori = product.kategori,
                marka = product.marka,
                siparisAdeti = 1 // her artırmada 1 ekle
            )
            loadBasketProducts()
        }
    }
    fun decreaseQuantity(product: ProductsMyBasket) {
        viewModelScope.launch {
            val allItems = productsRepository.loadBasketProducts()

            // Aynı üründen birden fazla varsa, içlerinden bir tanesini seç
            val matchingItems = allItems?.filter {
                it.ad == product.ad && it.marka == product.marka && it.resim == product.resim
            }

            // İlk bulunanı sil
            matchingItems?.firstOrNull()?.let {
                productsRepository.deleteFromBasket(it.sepetId)
            }

            loadBasketProducts()
        }
    }
}