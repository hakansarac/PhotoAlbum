package com.hakansarac.photoalbum.utils

class ProductRepository (private val api : ProductService):SafeRequest(){
    suspend fun getProducts() = apiRequest{ api.getProducts() }
}