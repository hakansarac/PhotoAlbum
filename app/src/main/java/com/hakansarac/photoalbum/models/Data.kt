package com.hakansarac.photoalbum.models

data class Data(
        val image: String,
        val itemType: String,
        val photoCount: Int,
        val price: Price,
        val products: List<Product>,
        val shortDescription: String,
        val title: String
)