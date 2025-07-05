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
class MainViewModel @Inject constructor(var productsRepository : ProductsRepository) : ViewModel() {
    var productsList = MutableLiveData<List<Products>>()

    init {
        loadProducts()
    }


    fun loadProducts() {
        viewModelScope.launch {
            productsList.value = productsRepository.loadProducts()
            println(productsList.value)
        }
    }

    fun search(searchText: String) {
        CoroutineScope(Dispatchers.Main).launch {
            productsList.value = productsRepository.search(searchText)
        }

    }

}