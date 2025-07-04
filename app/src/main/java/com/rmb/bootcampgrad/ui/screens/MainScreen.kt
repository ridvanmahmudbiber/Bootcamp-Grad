package com.rmb.bootcampgrad.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.rmb.bootcampgrad.R
import com.rmb.bootcampgrad.data.entity.Products
import com.rmb.bootcampgrad.databinding.MainScreenBinding
import com.rmb.bootcampgrad.ui.adapter.ProductsAdapter
import com.rmb.bootcampgrad.ui.viewmodel.MainViewModel
import com.rmb.bootcampgrad.ui.viewmodel.ProductDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreen : Fragment() {
    private lateinit var binding: MainScreenBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainScreenBinding.inflate(inflater, container, false)

        viewModel.productsList.observe(viewLifecycleOwner) {
            val productsAdapter = ProductsAdapter(requireContext(), it, viewModel)
            binding.recyclerViewMain.adapter = productsAdapter

        }

        binding.recyclerViewMain.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {

                viewModel.search(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.search(query)
                return true
            }

        })

        binding.cartIcon.setOnClickListener {
            it.findNavController().navigate(R.id.toBasketScreen)
        }

        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: MainViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadProducts()
    }

}