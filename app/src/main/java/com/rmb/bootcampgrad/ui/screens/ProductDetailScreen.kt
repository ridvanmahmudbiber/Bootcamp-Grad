package com.rmb.bootcampgrad.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.rmb.bootcampgrad.R
import com.rmb.bootcampgrad.databinding.ProductDetailScreenBinding
import com.rmb.bootcampgrad.ui.viewmodel.ProductDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailScreen : Fragment() {
    private lateinit var binding: ProductDetailScreenBinding
    private lateinit var viewModel: ProductDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProductDetailScreenBinding.inflate(inflater, container, false)

        val bundle: ProductDetailScreenArgs by navArgs()
        val product = bundle.product

        binding.imageViewProductDetail.setImageResource(
            resources.getIdentifier(product.image,"drawable",requireContext().packageName)
        )

        binding.tvProductName.text = product.name
        binding.tvProductBrand.text = product.brand
        binding.tvProductCategory.text = "Kategori: ${product.category}"
        binding.tvTotalPrice.text = "${product.price} ₺"
        binding.tvProductPrice.text = "${product.price} ₺"
        binding.tvProductQuantity.text = "1"

        binding.btnAddToCart.setOnClickListener {

        }

        binding.btnAdd.setOnClickListener {
            var quantity = binding.tvProductQuantity.text.toString().toInt() + 1
            binding.tvProductQuantity.text = quantity.toString()
            binding.tvTotalPrice.text = "${product.price * quantity} ₺"
        }

        binding.btnReduce.setOnClickListener {
            var quantity = binding.tvProductQuantity.text.toString().toInt() - 1
            if (quantity == 0) {
                quantity = 1
            }
            binding.tvProductQuantity.text = quantity.toString()
            binding.tvTotalPrice.text = "${product.price * quantity} ₺"
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: ProductDetailViewModel by viewModels()
        viewModel = tempViewModel
    }

}