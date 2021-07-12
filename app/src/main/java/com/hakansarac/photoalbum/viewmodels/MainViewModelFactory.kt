package com.hakansarac.photoalbum.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hakansarac.photoalbum.utils.ProductRepository

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val repository: ProductRepository):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}