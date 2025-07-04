package com.rmb.bootcampgrad.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.rmb.bootcampgrad.R
import com.rmb.bootcampgrad.data.entity.Products
import com.rmb.bootcampgrad.databinding.BasketScreenBinding
import com.rmb.bootcampgrad.ui.adapter.MyCartProductsAdapter
import com.rmb.bootcampgrad.ui.adapter.ProductsAdapter

class BasketScreen : Fragment() {
    private lateinit var binding: BasketScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BasketScreenBinding.inflate(inflater, container, false)

        val productsList = ArrayList<Products>()
        val p1 = Products(1,"Saat", "yildiz","Saat",100,"Rolex")
        val p2 = Products(2,"Çanta","yildiz","Saat",500,"Rolex")
        val p3 = Products(3,"Kalem","yildiz","Saat",250,"Rolex")
        productsList.add(p1)
        productsList.add(p2)
        productsList.add(p3)

        val myCartAdapter = MyCartProductsAdapter(requireContext(), productsList)
        binding.recyclerViewMyCart.adapter = myCartAdapter

        binding.recyclerViewMyCart.layoutManager = LinearLayoutManager(requireContext())



        return binding.root
    }
}