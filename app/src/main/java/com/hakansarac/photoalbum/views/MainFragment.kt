package com.hakansarac.photoalbum.views

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hakansarac.photoalbum.R
import com.hakansarac.photoalbum.utils.ProductRepository
import com.hakansarac.photoalbum.utils.ProductService
import com.hakansarac.photoalbum.viewmodels.MainViewModel
import com.hakansarac.photoalbum.viewmodels.MainViewModelFactory
import com.hakansarac.photoalbum.views.adapters.MainAdapter

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var factory: MainViewModelFactory
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val api = ProductService()
        val repository = ProductRepository(api)
        factory = MainViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this,factory).get(MainViewModel::class.java)
        viewModel.getProducts()
        viewModel.products.observe(viewLifecycleOwner, Observer{ products ->
            requireView().findViewById<RecyclerView>(R.id.rv_main).also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = MainAdapter(products)
            }
        })
    }
}