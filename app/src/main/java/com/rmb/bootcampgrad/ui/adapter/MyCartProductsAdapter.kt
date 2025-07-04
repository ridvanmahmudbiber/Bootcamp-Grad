package com.rmb.bootcampgrad.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.rmb.bootcampgrad.data.entity.Products
import com.rmb.bootcampgrad.databinding.MyCartCardDesignBinding
import com.rmb.bootcampgrad.ui.screens.BasketScreenDirections
import com.rmb.bootcampgrad.ui.screens.BasketScreenDirections.Companion.basketScreenToProductDetailScreen
import com.rmb.bootcampgrad.ui.screens.MainScreenDirections

class MyCartProductsAdapter(var mContext: Context, var productsList:List<Products>)
    : RecyclerView.Adapter<MyCartProductsAdapter.MyCartCardDesignHolder>() {

    inner class MyCartCardDesignHolder(var binding: MyCartCardDesignBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCartCardDesignHolder {
        val binding = MyCartCardDesignBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return MyCartCardDesignHolder(binding)
    }



    override fun onBindViewHolder(holder: MyCartCardDesignHolder, position: Int) {
        val basketProduct = productsList.get(position)
        val design = holder.binding

        design.imageViewCardMyBasket.setImageResource(
            mContext.resources.getIdentifier(basketProduct.image,"drawable",mContext.packageName)
        )

        design.tvBasketCartProductBrandName.text = "${basketProduct.brand} ${basketProduct.name}"
        design.tvCardTotalPrice.text = "${basketProduct.price} ₺"
        design.tvBasketCardProductPrice.text = "${basketProduct.price} ₺"


        design.cardViewMyBasket.setOnClickListener {
            val basketScreenToProductDetailScreen = BasketScreenDirections.basketScreenToProductDetailScreen(
                product = productsList[position])
            it.findNavController().navigate(basketScreenToProductDetailScreen)
        }


    }
    override fun getItemCount(): Int {
        return productsList.size

    }


}