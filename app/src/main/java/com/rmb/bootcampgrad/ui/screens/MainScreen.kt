package com.rmb.bootcampgrad.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.rmb.bootcampgrad.R
import com.rmb.bootcampgrad.data.entity.Products
import com.rmb.bootcampgrad.databinding.MainScreenBinding
import com.rmb.bootcampgrad.ui.adapter.ProductsAdapter

class MainScreen : Fragment() {
    private lateinit var binding: MainScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainScreenBinding.inflate(inflater, container, false)


        val productsList = ArrayList<Products>()
        val p1 = Products(1,"Saat", "yildiz","Saat",100,"Rolex")
        val p2 = Products(2,"Çanta","yildiz","Saat",500,"Rolex")
        val p3 = Products(3,"Kalem","yildiz","Saat",250,"Rolex")
        productsList.add(p1)
        productsList.add(p2)
        productsList.add(p3)

        val productsAdapter = ProductsAdapter(requireContext(), productsList)
        binding.recyclerViewMain.adapter = productsAdapter

        binding.recyclerViewMain.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)


        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {

                search(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                search(query)
                return true
            }

        })

        binding.cartIcon.setOnClickListener {
            it.findNavController().navigate(R.id.toBasketScreen)
        }


        return binding.root
    }
    fun search(searchText: String) {

    }
}