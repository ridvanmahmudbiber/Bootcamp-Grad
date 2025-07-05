package com.rmb.bootcampgrad.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.rmb.bootcampgrad.R
import com.rmb.bootcampgrad.databinding.MainScreenBinding
import com.rmb.bootcampgrad.ui.adapter.ProductsAdapter
import com.rmb.bootcampgrad.ui.viewmodel.MainViewModel
import com.rmb.bootcampgrad.ui.viewmodel.ProductDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreen : Fragment() {
    private lateinit var binding: MainScreenBinding
    private val viewModel: MainViewModel by viewModels()
    private val productViewModel: ProductDetailViewModel by viewModels()
        private lateinit var productsAdapter: ProductsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainScreenBinding.inflate(inflater, container, false)
        productsAdapter = ProductsAdapter(requireContext(), emptyList(), productViewModel)
        binding.recyclerViewMain.adapter = productsAdapter

        viewModel.productsList.observe(viewLifecycleOwner) {
            productsAdapter.updateList(it)
        }

        binding.recyclerViewMain.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    viewModel.search(newText)
                    println(newText)
                }
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.search(query)
                    println(query)

                }
                return true
            }

        })
        binding.homeIcon.setOnClickListener {
            it.findNavController().navigate(R.id.mainScreen)
        }
        binding.cartIcon.setOnClickListener {
            it.findNavController().navigate(R.id.toBasketScreen)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
      viewModel.loadProducts()
    }

}