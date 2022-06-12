package com.example.projecttest.productList.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projecttest.dataBase.db.ItemProductDatabase
import com.example.projecttest.dataBase.repository.ItemProductRepository
import com.example.projecttest.dataBase.viewModel.ItemProductViewModel
import com.example.projecttest.dataBase.viewModel.ItemProductViewModelFactory
import com.example.projecttest.databinding.FragmentProductListBinding
import com.example.projecttest.productList.adapter.ProductListAdapter
import com.example.projecttest.productList.repository.ProductRepository
import com.example.projecttest.productList.viewModel.ProductViewModel
import com.example.projecttest.productList.viewModel.ProductViewModelFactory
import kotlinx.coroutines.NonDisposableHandle.parent

class ProductListFragment : Fragment() {

    private var _binding: FragmentProductListBinding?  = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProductListBinding.inflate(inflater,container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()

    }

    private fun setUpViewModel() {
        Log.d("ProductFragment" , "Reached")
        val productViewModel = ViewModelProvider(this ,
            ProductViewModelFactory(ProductRepository())
        )[ProductViewModel::class.java]

        val roomViewModel = ViewModelProvider(this , ItemProductViewModelFactory(repository = ItemProductRepository(
            ItemProductDatabase(context!!)
        )))[ItemProductViewModel::class.java]

        productViewModel.getProductViewModel()
        productViewModel.productList.observe(viewLifecycleOwner , Observer {
            if (it.isSuccessful){
                Log.d("CartFragment" , "this is the productList ${it.body()}")
                binding.recyclerView.layoutManager = LinearLayoutManager(context)
                binding.recyclerView.adapter = ProductListAdapter(it.body()!! ,  roomViewModel)
                Log.d("ProductFragment" , "this is from productFragment $it")
            }else{
                Log.d("ProductFragment" , "this is the productList failed ${it.errorBody()}")
            }
        })
    }


}