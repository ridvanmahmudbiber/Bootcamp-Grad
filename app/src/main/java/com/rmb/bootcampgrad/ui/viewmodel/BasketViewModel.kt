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

    fun deleteFromBasket(id: Int) {
        viewModelScope.launch {
            productsRepository.deleteFromBasket(id)
            loadBasketProducts()
        }
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

}