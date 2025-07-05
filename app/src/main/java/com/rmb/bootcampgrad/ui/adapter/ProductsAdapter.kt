package com.rmb.bootcampgrad.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.rmb.bootcampgrad.R
import com.rmb.bootcampgrad.data.entity.Products
import com.rmb.bootcampgrad.databinding.MainCardDesignBinding
import com.rmb.bootcampgrad.ui.screens.MainScreenDirections
import com.rmb.bootcampgrad.ui.viewmodel.BasketViewModel
import com.rmb.bootcampgrad.ui.viewmodel.MainViewModel
import com.rmb.bootcampgrad.ui.viewmodel.ProductDetailViewModel

class ProductsAdapter(
    var mContext: Context,
    var productsList: List<Products>,
    val viewModel: ProductDetailViewModel
) : RecyclerView.Adapter<ProductsAdapter.MainCardDesignHolder>() {

    inner class MainCardDesignHolder(var binding: MainCardDesignBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainCardDesignHolder {
        val binding = MainCardDesignBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return MainCardDesignHolder(binding)

    }

    override fun onBindViewHolder(holder: MainCardDesignHolder, position: Int) {
        val product = productsList.get(position)
        val design = holder.binding

        val url = "http://kasimadalan.pe.hu/urunler/resimler/${product.resim}"
        Glide.with(mContext).load(url).override(512, 512).into(design.imageViewCardProduct)
        Log.e("ProductsAdapter", "onBindViewHolder: ${product.ad}, ${product.marka}")
        design.productBrandName.text = "${product.marka}"
        design.tvMainProductName.text = "${product.ad}"
        design.tvCardProductPrice.text = "${product.fiyat} ₺"
        design.imageViewAddCart.setOnClickListener {
            viewModel.addToBasket(
                product.ad,
                product.resim,
                product.kategori,
                product.fiyat,
                product.marka,
                1
            )
            Snackbar.make(holder.itemView, "Ürün sepete başarıyla eklendi", Snackbar.LENGTH_SHORT)
                .setAction("Sepete git") {
                    val navController = holder.itemView.findNavController()
                    navController.navigate(R.id.toBasketScreen)
                }.show()
        }
        design.cardViewProduct.setOnClickListener {
            val toProductDetailScreen =
                MainScreenDirections.toProductDetailScreen(product = product)
            it.findNavController().navigate(toProductDetailScreen)
        }


    }
    fun updateList(newList: List<Products>) {
        productsList = newList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

}