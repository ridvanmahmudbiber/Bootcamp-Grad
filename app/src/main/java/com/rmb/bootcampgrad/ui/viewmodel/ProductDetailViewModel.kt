package com.rmb.bootcampgrad.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.rmb.bootcampgrad.data.repo.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(var productsRepository : ProductsRepository) : ViewModel()  {

}


