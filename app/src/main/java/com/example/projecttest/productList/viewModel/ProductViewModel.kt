package com.example.projecttest.productList.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projecttest.productList.model.ProductItem
import com.example.projecttest.productList.repository.ProductRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {
    val productList : MutableLiveData<Response<List<ProductItem>>> = MutableLiveData()

    fun getProductViewModel(){
        viewModelScope.launch {
            Log.d("ProductFragment" , "From productViewModel")
            productList.value = repository.getProductList()
        }
    }
}