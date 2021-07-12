package com.hakansarac.photoalbum.models

data class Product(
        val image: String,
        val photoCount: Int,
        val price: PriceX,
        val shortDescription: String,
        val title: String
)