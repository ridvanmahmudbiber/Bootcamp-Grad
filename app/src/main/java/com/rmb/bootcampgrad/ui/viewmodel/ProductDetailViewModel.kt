package com.rmb.bootcampgrad.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmb.bootcampgrad.data.repo.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(var productsRepository : ProductsRepository) : ViewModel()  {

    fun addToBasket(ad: String,
                    resim: String,
                    kategori: String,
                    fiyat: Int,
                    marka: String,
                    siparisAdeti: Int) {
        viewModelScope.launch {
            productsRepository.addToBasket(ad,resim,kategori,fiyat,marka,siparisAdeti)
            Log.e("ProductDetailViewModel", "başarılı")
        }
        println("$ad $resim $kategori $fiyat $marka $siparisAdeti")
    }
}


