package com.rmb.bootcampgrad.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.rmb.bootcampgrad.data.entity.Products
import com.rmb.bootcampgrad.databinding.MainCardDesignBinding
import com.rmb.bootcampgrad.ui.screens.MainScreenDirections
import com.rmb.bootcampgrad.ui.viewmodel.BasketViewModel
import com.rmb.bootcampgrad.ui.viewmodel.MainViewModel

class ProductsAdapter(var mContext: Context,
                      var productsList:List<Products>,
                      var viewModel: MainViewModel
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

        design.imageViewCardProduct.setImageResource(
            mContext.resources.getIdentifier(product.image,"drawable",mContext.packageName)
        )

        design.productBrandName.text = "${product.brand} ${product.name}"
        design.tvCardProductPrice.text = "${product.price} ₺"

        design.cardViewProduct.setOnClickListener {
            val toProductDetailScreen = MainScreenDirections.toProductDetailScreen(product = product)
            it.findNavController().navigate(toProductDetailScreen)
        }


    }

    override fun getItemCount(): Int {
        return productsList.size
    }

}