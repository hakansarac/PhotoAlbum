package com.hakansarac.photoalbum.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hakansarac.photoalbum.R
import com.hakansarac.photoalbum.models.Product
import com.squareup.picasso.Picasso

class CategoryItemAdapter(private val products : List<Product>)
    : RecyclerView.Adapter<CategoryItemAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =  LayoutInflater.from(parent.context)
            .inflate(R.layout.category_item,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.textViewTitle.text = product.title
        holder.textViewPrice.text = product.price.displayPriceString
        Picasso.get().load(product.image).into(holder.imageView)
    }


    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){

        val textViewTitle : TextView = view.findViewById(R.id.tv_title_product_carousel)
        val imageView: ImageView = view.findViewById(R.id.image_product_carousel)
        val textViewPrice : TextView = view.findViewById(R.id.tv_price_product_carousel)

    }
}