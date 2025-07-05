package com.rmb.bootcampgrad.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
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

        val url = "http://kasimadalan.pe.hu/urunler/resimler/${product.resim}"
        Glide.with(requireContext()).load(url).override(512,512).into(binding.imageViewProductDetail)

        binding.tvProductName.text = product.ad
        binding.tvProductBrand.text = product.marka
        binding.tvProductCategory.text = "Kategori: ${product.kategori}"
        binding.tvTotalPrice.text = "${product.fiyat} ₺"
        binding.tvProductPrice.text = "${product.fiyat} ₺"
        binding.tvProductQuantity.text = "1"

        binding.btnAddToCart.setOnClickListener { card->
            viewModel.addToBasket(product.ad, product.resim, product.kategori, product.fiyat, product.marka, binding.tvProductQuantity.text.toString().toInt())
            Snackbar.make(requireView(), "Ürün sepete başarıyla eklendi", Snackbar.LENGTH_SHORT)
                .setAction("Sepete git"){
                    findNavController().navigate(R.id.action_productDetailScreen_to_basketScreen)
                }.show()
        }

        binding.btnAdd.setOnClickListener {
            var quantity = binding.tvProductQuantity.text.toString().toInt() + 1
            binding.tvProductQuantity.text = quantity.toString()
            binding.tvTotalPrice.text = "${product.fiyat * quantity} ₺"
        }

        binding.btnReduce.setOnClickListener {
            var quantity = binding.tvProductQuantity.text.toString().toInt() - 1
            if (quantity == 0) {
                quantity = 1
            }
            binding.tvProductQuantity.text = quantity.toString()
            binding.tvTotalPrice.text = "${product.fiyat * quantity} ₺"
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: ProductDetailViewModel by viewModels()
        viewModel = tempViewModel
    }

}