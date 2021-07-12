package com.hakansarac.photoalbum.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hakansarac.photoalbum.R
import com.hakansarac.photoalbum.models.Data
import com.squareup.picasso.Picasso


class MainAdapter(private val parents : List<Data>) :    RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    companion object {
        const val VIEW_TYPE_PRODUCT = 1
        const val VIEW_TYPE_CAROUSEL = 2
    }

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RecyclerView.ViewHolder {
        if(viewType== VIEW_TYPE_CAROUSEL){
            return ViewHolderCarousel(
                    LayoutInflater.from(parent.context)
                            .inflate(R.layout.recycler_category,parent,false)
            )
        }
        return ViewHolderProduct(LayoutInflater.from(parent.context)
                .inflate(R.layout.card_product_item,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return parents.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder,
                                  position: Int) {
        if(parents[position].itemType.toString()=="Carousel")
            (holder as ViewHolderCarousel).bind(position)
        else
            (holder as ViewHolderProduct).bind(position)

    }

    override fun getItemViewType(position: Int): Int {
        if(parents[position].itemType.toString() == "Carousel")
            return 2
        return 1
    }

    //TODO: view isimlerini duzelt
    inner class ViewHolderCarousel(view : View) : RecyclerView.ViewHolder(view){
        private val recyclerView : RecyclerView = view.findViewById(R.id.rv_child)
        private val textView:TextView = view.findViewById(R.id.textView)
        fun bind(position:Int){
            val parent = parents[position]
            if(parent.itemType.toString() == "Carousel"){
                textView.text = parent.title

                val childLayoutManager = LinearLayoutManager(recyclerView.context,LinearLayoutManager.HORIZONTAL,false)
                childLayoutManager.initialPrefetchItemCount = 4
                recyclerView.apply {
                    layoutManager = childLayoutManager
                    adapter = CategoryItemAdapter(parent.products)
                    setRecycledViewPool(viewPool)
                }
            }
        }
    }

    inner class ViewHolderProduct(view : View) : RecyclerView.ViewHolder(view){
        private val imageViewProduct : ImageView = view.findViewById(R.id.image_product)
        private val textViewTitle : TextView = view.findViewById(R.id.tv_title_product)
        private val textViewPrice : TextView = view.findViewById(R.id.tv_price_product)

        fun bind(position: Int){
            val product = parents[position]
            if(product.itemType.toString() == "Product"){
                //Glide.with(this.imageViewProduct.context)
                //    .load(product.image)
                //    .into(this.imageViewProduct)
                Picasso.get().load(product.image).into(imageViewProduct)
                textViewTitle.text = product.title
                textViewPrice.text = product.price.originalPriceString
            }

        }
    }

}