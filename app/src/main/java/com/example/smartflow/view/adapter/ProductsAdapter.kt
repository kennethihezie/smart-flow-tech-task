package com.example.smartflow.view.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.smartflow.R
import com.example.smartflow.databinding.ProductItemBinding
import com.example.smartflow.model.Product
import com.example.smartflow.view.viewholder.ProductsViewHolder

/***
 * Created by collins Ihezie
 * on 24/03/2023
 */

class ProductsAdapter(private val context: Fragment) : RecyclerView.Adapter<ProductsViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context.requireContext())
    private var products = emptyList<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        ProductItemBinding.inflate(inflater, parent, false).apply {
            return ProductsViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val currentProduct = products[position]
        val bundle = Bundle()
        if(currentProduct.imageLink != null) {
            holder.binding.productImg.load(currentProduct.imageLink){
                crossfade(durationMillis = 2000)
                transformations(RoundedCornersTransformation(12.5f))
            }
        } else {
            holder.binding.cardView.visibility = View.GONE
        }


        holder.binding.productName.text = currentProduct.name
        holder.binding.price.text = "${currentProduct.pricesSign ?: ""}${currentProduct.price}"
        holder.binding.branding.text = currentProduct.brand
        holder.binding.category.text = currentProduct.category

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

class ProductsPagingAdapter(private val context: Fragment) : PagingDataAdapter<Product, ProductsViewHolder>(DIFF_CALLBACK){
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldProduct: Product, newProduct: Product): Boolean =
                oldProduct.id == newProduct.id

            override fun areContentsTheSame(oldProduct: Product, newProduct: Product): Boolean =
                oldProduct == newProduct
        }
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val currentProduct = getItem(position)

        if(currentProduct?.imageLink != null) {
            holder.binding.productImg.load(currentProduct.imageLink){
                crossfade(durationMillis = 2000)
                transformations(RoundedCornersTransformation(12.5f))

                error(R.drawable.ic_baseline_broken_image_24)
            }
        } else {
            holder.binding.cardView.visibility = View.GONE
        }


        holder.binding.productName.text = currentProduct?.name
        holder.binding.price.text = "${currentProduct?.pricesSign ?: ""}${currentProduct?.price ?: ""}"
        if(currentProduct?.brand != null) {
            holder.binding.branding.text = currentProduct.brand
        } else {
            holder.binding.branding.visibility = View.GONE
        }

        if(currentProduct?.category != null){
            holder.binding.category.text = currentProduct.category
        } else {
            holder.binding.category.visibility = View.GONE
        }

        holder.binding.root.setOnClickListener {
            context.findNavController().navigate(
                R.id.action_productsFragment_to_productDetailsFragment, bundleOf(Pair("product", currentProduct))
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

}