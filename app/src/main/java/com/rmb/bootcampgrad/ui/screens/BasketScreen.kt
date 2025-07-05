package com.rmb.bootcampgrad.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rmb.bootcampgrad.databinding.BasketScreenBinding
import com.rmb.bootcampgrad.ui.adapter.BasketProductsAdapter
import com.rmb.bootcampgrad.ui.viewmodel.BasketViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketScreen : Fragment() {
    private lateinit var binding: BasketScreenBinding
    private val viewModel: BasketViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BasketScreenBinding.inflate(inflater, container, false)
        binding.recyclerViewMyCart.layoutManager = LinearLayoutManager(requireContext())

        viewModel.basketProductsList.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()) {
                binding.tvEmptyBasket.visibility = View.VISIBLE
                binding.recyclerViewMyCart.visibility = View.GONE
            } else {
                binding.tvEmptyBasket.visibility = View.GONE
                binding.recyclerViewMyCart.visibility = View.VISIBLE
                val basketProductsAdapter = BasketProductsAdapter(requireContext(), it, viewModel)
                binding.recyclerViewMyCart.adapter = basketProductsAdapter
                binding.tvTotalMyCart.text = "${it?.sumOf { it.fiyat * it.siparisAdeti }} ₺"
            }
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadBasketProducts()
    }
}