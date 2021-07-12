package com.hakansarac.photoalbum.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hakansarac.photoalbum.Coroutines
import com.hakansarac.photoalbum.models.Data
import com.hakansarac.photoalbum.utils.ProductRepository
import kotlinx.coroutines.Job

class MainViewModel(private val repository: ProductRepository) : ViewModel() {

    private lateinit var job : Job
    private val _products = MutableLiveData<List<Data>>()
    val products : LiveData<List<Data>>
        get() = _products

    fun getProducts(){
        job = Coroutines.ioThenMain(
            {repository.getProducts().data},
            {
                _products.value = it
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }
}