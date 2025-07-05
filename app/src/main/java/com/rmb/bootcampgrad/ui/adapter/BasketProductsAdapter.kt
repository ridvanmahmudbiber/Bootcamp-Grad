package com.rmb.bootcampgrad.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.rmb.bootcampgrad.core.APIPaths
import com.rmb.bootcampgrad.core.Constants
import com.rmb.bootcampgrad.data.entity.Products
import com.rmb.bootcampgrad.data.entity.ProductsMyBasket
import com.rmb.bootcampgrad.databinding.MyCartCardDesignBinding
import com.rmb.bootcampgrad.ui.screens.BasketScreenDirections
import com.rmb.bootcampgrad.ui.viewmodel.BasketViewModel

class BasketProductsAdapter(
    var mContext: Context,
    var productsList:List<ProductsMyBasket>,
    var viewModel: BasketViewModel
)
    : RecyclerView.Adapter<BasketProductsAdapter.MyCartCardDesignHolder>() {

    inner class MyCartCardDesignHolder(var binding: MyCartCardDesignBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCartCardDesignHolder {
        val binding = MyCartCardDesignBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return MyCartCardDesignHolder(binding)
    }

    override fun onBindViewHolder(holder: MyCartCardDesignHolder, position: Int) {
        val basketProduct = productsList[position]
        val design = holder.binding

        val url = "${APIPaths.imageBaseUrl}${basketProduct.resim}"
        Glide.with(mContext).load(url).override(512,512).into(design.imageViewCardMyBasket)

        design.tvBasketCartProductBrandName.text = basketProduct.marka
        design.tvBasketCartProductName.text = basketProduct.ad
        design.tvBasketCardProductPrice.text = "${basketProduct.fiyat} ₺"
        design.tvProductQuantity.text = basketProduct.siparisAdeti.toString() // <--- adet texti

        // Toplam fiyat (adet * birim fiyat)
        val toplamFiyat = basketProduct.fiyat * basketProduct.siparisAdeti
        design.tvCardTotalPrice.text = "$toplamFiyat ₺"

        design.cardViewMyBasket.setOnClickListener {
            val product = Products(
                id = 0, // Sepet nesnesinde id olmayabilir, 0 veya başka uygun bir değer ver
                ad = basketProduct.ad,
                resim = basketProduct.resim,
                kategori = basketProduct.kategori,
                fiyat = basketProduct.fiyat,
                marka = basketProduct.marka
            )
            val action = BasketScreenDirections.basketScreenToProductDetailScreen(product)
            it.findNavController().navigate(action)
        }


        design.imageViewDelete.setOnClickListener {
            Snackbar.make(it, "${basketProduct.marka} ${basketProduct.ad} silinsin mi?", Snackbar.LENGTH_SHORT)
                .setAction("Evet"){
                    viewModel.deleteAllSameProducts(
                        ad = basketProduct.ad,
                        marka = basketProduct.marka
                    )
                }.show()
        }

        design.btnAdd.setOnClickListener {
            viewModel.increaseQuantity(basketProduct)
        }

        design.btnReduce.setOnClickListener { view ->
            if (basketProduct.siparisAdeti > 1) {
                viewModel.decreaseQuantity(basketProduct)
            } else {
                Snackbar.make(view, "${basketProduct.marka} ${basketProduct.ad} silinsin mi?", Snackbar.LENGTH_SHORT)
                    .setAction("Evet") {
                        viewModel.deleteAllSameProducts(
                            ad = basketProduct.ad,
                            marka = basketProduct.marka
                        )
                    }.show()
            }
        }
    }

    override fun getItemCount(): Int = productsList.size
}