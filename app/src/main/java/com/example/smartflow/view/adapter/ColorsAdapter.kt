package com.example.smartflow.view.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smartflow.databinding.ColorItemBinding
import com.example.smartflow.model.ProductColor
import com.example.smartflow.view.viewholder.ColorsViewHolder

/***
 * Created by collins Ihezie
 * on 24/03/2023
 */

class ColorsAdapter(private val context: Context) : RecyclerView.Adapter<ColorsViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var colors = emptyList<ProductColor>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorsViewHolder {
        ColorItemBinding.inflate(inflater, parent, false).apply {
            return ColorsViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: ColorsViewHolder, position: Int) {
        val currentColor = colors[position]
        holder.binding.color.setBackgroundColor(Color.parseColor(currentColor.hexValue))
    }

    override fun getItemCount(): Int = colors.size

    internal fun setColors(colors: List<ProductColor>){
        this.colors = colors
        notifyDataSetChanged()
    }

}