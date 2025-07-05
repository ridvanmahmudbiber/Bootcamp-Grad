package com.rmb.bootcampgrad.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
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

        val url = "http://kasimadalan.pe.hu/urunler/resimler/${basketProduct.resim}"
        Glide.with(mContext).load(url).override(512,512).into(design.imageViewCardMyBasket)

        design.tvBasketCartProductBrandName.text = "${basketProduct.marka} ${basketProduct.ad}"
        design.tvBasketCardProductPrice.text = "${basketProduct.fiyat} ₺"
        design.tvProductQuantity.text = basketProduct.siparisAdeti.toString() // <--- adet texti

        // Toplam fiyat (adet * birim fiyat)
        val toplamFiyat = basketProduct.fiyat * basketProduct.siparisAdeti
        design.tvCardTotalPrice.text = "$toplamFiyat ₺"

        design.imageViewDelete.setOnClickListener {

            Snackbar.make(it, "${basketProduct.marka} ${basketProduct.ad} silinsin mi?", Snackbar.LENGTH_SHORT)
                .setAction("Evet"){
                    viewModel.deleteAllSameProducts(
                        ad = basketProduct.ad,
                        marka = basketProduct.marka
                    )
                }.show()
        }
    }

   /* override fun onBindViewHolder(holder: MyCartCardDesignHolder, position: Int) {
        val basketProduct = productsList.get(position)
        val design = holder.binding

        val url = "http://kasimadalan.pe.hu/urunler/resimler/${basketProduct.resim}"
        Glide.with(mContext).load(url).override(512,512).into(design.imageViewCardMyBasket)


        design.tvBasketCartProductBrandName.text = "${basketProduct.marka} ${basketProduct.ad}"
        design.tvCardTotalPrice.text = "${basketProduct.fiyat} ₺"
        design.tvBasketCardProductPrice.text = "${basketProduct.fiyat} ₺"


//        design.cardViewMyBasket.setOnClickListener {
//            val basketScreenToProductDetailScreen = BasketScreenDirections.basketScreenToProductDetailScreen(
//                product = productsList[position])
//            it.findNavController().navigate(basketScreenToProductDetailScreen)
//        }

        design.imageViewDelete.setOnClickListener {
            Snackbar.make(it, "${basketProduct.marka} ${basketProduct.ad} silinsin mi?", Snackbar.LENGTH_SHORT)
                .setAction("YES"){
                    viewModel.deleteFromBasket(basketProduct.sepetId)
                }.show()

        }

    }*/
    override fun getItemCount(): Int {
        return productsList.size

    }
}