package com.example.projecttest.dataBase.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.projecttest.dataBase.repository.ItemProductRepository

class ItemProductViewModelFactory(private val repository: ItemProductRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ItemProductViewModel(repository) as T

    }
}