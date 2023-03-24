package com.example.smartflow.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.smartflow.R
import com.example.smartflow.databinding.SuggestedItemsBinding
import com.example.smartflow.model.Product
import com.example.smartflow.view.viewholder.SuggestedViewHolder

/***
 * Created by collins Ihezie
 * on 24/03/2023
 */

class SuggestedItemsAdapter(private val context: Fragment) : RecyclerView.Adapter<SuggestedViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context.requireContext())
    private var products = emptyList<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestedViewHolder {
        SuggestedItemsBinding.inflate(inflater, parent, false).apply {
            return SuggestedViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: SuggestedViewHolder, position: Int) {
        val currentProduct = products[position]

        holder.binding.imageView.load(currentProduct.imageLink){
            crossfade(durationMillis = 2000)
            transformations(RoundedCornersTransformation(12.5f))
            error(R.drawable.ic_baseline_broken_image_24)
        }

        holder.binding.textView.isSelected = true
        holder.binding.textView.text = currentProduct.name

        holder.binding.root.setOnClickListener {
            context.findNavController().navigate(
                R.id.action_productsFragment_to_productDetailsFragment, bundleOf(Pair("product", currentProduct))
            )
        }
    }

    override fun getItemCount(): Int = products.size

    internal fun setProducts(products: List<Product>){
        this.products = products
        notifyDataSetChanged()
    }

}