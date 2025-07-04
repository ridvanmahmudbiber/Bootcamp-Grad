package com.rmb.bootcampgrad.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rmb.bootcampgrad.data.entity.Products
import com.rmb.bootcampgrad.data.repo.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(var productsRepository : ProductsRepository) : ViewModel() {
    var productsList = MutableLiveData<List<Products>>()

    init {
        loadBasketProducts()
    }

    fun delete(id: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            productsRepository.delete(id)
            loadBasketProducts()
        }
    }

    fun loadBasketProducts() {
        CoroutineScope(Dispatchers.Main).launch {
            productsList.value = productsRepository.loadBasketProducts()
        }
    }
}