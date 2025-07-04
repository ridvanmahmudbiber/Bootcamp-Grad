package com.rmb.bootcampgrad.ui.screens

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
    private lateinit var viewModel: BasketViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BasketScreenBinding.inflate(inflater, container, false)

        viewModel.productsList.observe(viewLifecycleOwner) {
            val basketProductsAdapter = BasketProductsAdapter(requireContext(), it, viewModel)
            binding.recyclerViewMyCart.adapter = basketProductsAdapter

        }

        binding.recyclerViewMyCart.layoutManager = LinearLayoutManager(requireContext())


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: BasketViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadBasketProducts()
    }
}