package com.example.smartflow.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.smartflow.R
import com.example.smartflow.databinding.ProductDetailsFragmentBinding
import com.example.smartflow.model.Product
import com.example.smartflow.view.adapter.ColorsAdapter
import timber.log.Timber

/***
 * Created by collins Ihezie
 * on 24/03/2023
 */


class ProductDetailsFragment : Fragment() {
    private lateinit var binding: ProductDetailsFragmentBinding
    private var product: Product? = null
    private lateinit var adapter: ColorsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProductDetailsFragmentBinding.inflate(inflater, container, false)
        arguments.let {
            product = it?.get("product") as Product?
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ColorsAdapter(requireContext())

        binding.img.load(product?.imageLink){
            crossfade(durationMillis = 2000)
            transformations(RoundedCornersTransformation(12.5f))

            error(R.drawable.ic_baseline_broken_image_24)
        }

        binding.productName.text = product?.name
        binding.price.text = "${product?.pricesSign ?: ""}${product?.price}"
        if(product?.brand != null){
        } else {
            binding.branding.visibility = View.GONE
            binding.brandingImg.visibility = View.GONE
        }

        if(product?.category != null){
            binding.category.text = product?.brand
        } else {
            binding.category.visibility = View.GONE
            binding.categoryImg.visibility = View.GONE
        }

        if(product?.brand != null){
            binding.branding.text = product?.brand
        } else {
            binding.branding.visibility = View.GONE
            binding.brandingImg.visibility = View.GONE
        }

        if(product?.websiteLink != null){
            binding.website.text = product?.brand
        } else {
            binding.website.visibility = View.GONE
            binding.websiteImg.visibility = View.GONE
        }

        if(product?.tagList?.isEmpty() == true){
            binding.tags.visibility = View.GONE
            binding.tagsImg.visibility = View.GONE
        }

        if(product?.productColors == null){
            binding.colorsRv.visibility = View.GONE
        }

        if(product?.currency == null){
            binding.currency.visibility = View.GONE
        }

        if(product?.rating == null){
            binding.rating.visibility = View.GONE
        }

        if(product?.productType == null){
            binding.productType.visibility = View.GONE
        }

        binding.currencyTv.text = product?.currency
        binding.rating.text = product?.rating
        binding.productTypeTv.text = product?.productType
        binding.productDesc.text = product?.description

        binding.colorsRv.adapter = adapter
        binding.colorsRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        product?.productColors?.let { adapter.setColors(it) }

        binding.tags.text = product?.tagList?.joinToString(", ")
    }
}