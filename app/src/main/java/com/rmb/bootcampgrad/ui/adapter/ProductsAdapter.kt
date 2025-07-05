package com.rmb.bootcampgrad.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rmb.bootcampgrad.data.entity.Products
import com.rmb.bootcampgrad.databinding.MainCardDesignBinding
import com.rmb.bootcampgrad.ui.screens.MainScreenDirections
import com.rmb.bootcampgrad.ui.viewmodel.BasketViewModel
import com.rmb.bootcampgrad.ui.viewmodel.MainViewModel

class ProductsAdapter(var mContext: Context,
                      var productsList:List<Products>,
)
    : RecyclerView.Adapter<ProductsAdapter.MainCardDesignHolder>() {

    inner class MainCardDesignHolder(var binding: MainCardDesignBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainCardDesignHolder {
        val binding = MainCardDesignBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return MainCardDesignHolder(binding)

    }

    override fun onBindViewHolder(holder: MainCardDesignHolder, position: Int) {
        val product = productsList.get(position)
        val design = holder.binding

        val url = "http://kasimadalan.pe.hu/urunler/resimler/${product.resim}"
        Glide.with(mContext).load(url).override(512,512).into(design.imageViewCardProduct)

        design.productBrandName.text = "${product.marka} ${product.ad}"
        design.tvCardProductPrice.text = "${product.fiyat} ₺"

        design.cardViewProduct.setOnClickListener {
            val toProductDetailScreen = MainScreenDirections.toProductDetailScreen(product = product)
            it.findNavController().navigate(toProductDetailScreen)
        }


    }

    override fun getItemCount(): Int {
        return productsList.size
    }

}