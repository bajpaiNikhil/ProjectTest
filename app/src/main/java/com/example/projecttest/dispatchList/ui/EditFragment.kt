package com.example.projecttest.dispatchList.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projecttest.dataBase.db.ItemProductDatabase
import com.example.projecttest.dataBase.entities.ItemProduct
import com.example.projecttest.dataBase.repository.ItemProductRepository
import com.example.projecttest.dataBase.viewModel.ItemProductViewModel
import com.example.projecttest.dataBase.viewModel.ItemProductViewModelFactory
import com.example.projecttest.databinding.FragmentEditBinding
import com.example.projecttest.dispatchList.adapter.DispatchListAdapter


class editFragment : Fragment() {

    private var _binding : FragmentEditBinding? = null
    private val binding get() = _binding!!

    private val dispatchList : MutableList<ItemProduct> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_edit, container, false)

        _binding = FragmentEditBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViewModel()


    }

    private fun setUpViewModel() {
        val roomViewModel = ViewModelProvider(
            this  ,
            ItemProductViewModelFactory(
                repository = ItemProductRepository(ItemProductDatabase(context!!))))[ItemProductViewModel::class.java]

        roomViewModel.getAllItemProductList().observe(viewLifecycleOwner , Observer {
            for (item in it){
                if (item.dispatchList){
                    dispatchList.add(item)
                }
            }
            Log.d("DispatchFragment" , "this is the dispatch list ${dispatchList.distinct()}")
            binding.dispatchRecyclerView.layoutManager = LinearLayoutManager(context)
            binding.dispatchRecyclerView.adapter = DispatchListAdapter(dispatchList.distinct() , roomViewModel)
            dispatchList.clear()
        })
    }


}



