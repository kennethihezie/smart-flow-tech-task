package com.example.smartflow.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartflow.databinding.ProductsFragmentBinding
import com.example.smartflow.repository.SmartFlowRepository
import com.example.smartflow.view.adapter.ProductsPagingAdapter
import com.example.smartflow.view.adapter.SuggestedItemsAdapter
import com.example.smartflow.viewmodel.SmartFlowViewModel
import com.example.smartflow.viewmodel.SmartFlowViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject

/***
 * Created by collins Ihezie
 * on 24/03/2023
 */

class ProductsFragment : Fragment() {
    private val smartFlowViewModelFactory: SmartFlowViewModelFactory by inject()
    private val smartFlowViewModel: SmartFlowViewModel by viewModels { smartFlowViewModelFactory }

    private lateinit var binding: ProductsFragmentBinding
    private lateinit var adapter: SuggestedItemsAdapter
    private lateinit var vAdapter: ProductsPagingAdapter

    private val coroutineScope by lazy {
        CoroutineScope(Dispatchers.IO)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProductsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       binding.progressBar.visibility = View.VISIBLE

        initAdapter()

        registerProductsObserver()

        val a = "j"
    }

    private fun initAdapter(){
        adapter = SuggestedItemsAdapter(this)
        vAdapter = ProductsPagingAdapter(this)

        binding.horizontalItems.adapter = adapter
        binding.horizontalItems.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.verticalItems.adapter = vAdapter
        binding.verticalItems.layoutManager = LinearLayoutManager(requireContext())
        binding.verticalItems.isNestedScrollingEnabled = false
    }


    private fun registerProductsObserver(){
        coroutineScope.launch {
            withContext(Dispatchers.Main){
                smartFlowViewModel.data.collectLatest {
                    vAdapter.submitData(it)
                }
            }
        }

        coroutineScope.launch {
            val products = smartFlowViewModel.getProducts()
            withContext(Dispatchers.Main){
                if(products.isNotEmpty()){
                    binding.container.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    adapter.setProducts(products)
                }
            }
        }
    }
}