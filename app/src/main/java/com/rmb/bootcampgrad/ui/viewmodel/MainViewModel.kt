package com.rmb.bootcampgrad.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmb.bootcampgrad.data.entity.Products
import com.rmb.bootcampgrad.data.repo.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(var productsRepository: ProductsRepository) : ViewModel() {
    var productsList = MutableLiveData<List<Products>>()

    init {
        loadProducts()
    }

     fun loadProducts() {
        viewModelScope.launch {
            productsList.value = productsRepository.loadProducts()
        }
    }

    fun search(searchText: String) {
        viewModelScope.launch {
            val result = productsRepository.search(searchText)
            productsList.value = result
            println("Result : ${productsList.value}")
        }
    }
}