package com.hakansarac.photoalbum.utils

import com.hakansarac.photoalbum.models.ProductResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ProductService {

    @GET("sosyopix/android-challenge/master/data.json")
    suspend fun getProducts() : Response<ProductResponse>
    companion object{
        operator fun invoke() : ProductService{
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://raw.githubusercontent.com/")
                .build()
                .create(ProductService::class.java)
        }
    }
}