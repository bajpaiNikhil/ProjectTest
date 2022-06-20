package com.example.projecttest.pickedList.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projecttest.R
import com.example.projecttest.dataBase.db.ItemProductDatabase
import com.example.projecttest.dataBase.entities.ItemProduct
import com.example.projecttest.dataBase.repository.ItemProductRepository
import com.example.projecttest.dataBase.viewModel.ItemProductViewModel
import com.example.projecttest.dataBase.viewModel.ItemProductViewModelFactory
import com.example.projecttest.databinding.FragmentSettingBinding
import com.example.projecttest.pickedList.adapter.PickedListAdapter

class settingFragment : Fragment() {

    private var _binding : FragmentSettingBinding? = null
    private val binding get() = _binding!!

    private var pickedList : MutableList<ItemProduct> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSettingBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()
    }

    private fun setUpViewModel() {

        val roomViewModel = ViewModelProvider(this , ItemProductViewModelFactory(repository = ItemProductRepository(
            ItemProductDatabase(context!!)
        )
        )
        )[ItemProductViewModel::class.java]

        roomViewModel.getAllItemProductList().observe(viewLifecycleOwner , Observer {

            for (item in it){
                if(item.pickedList){
                    binding.pickedListAnimation.visibility = View.INVISIBLE
                    Log.d("PickedFragment" , "picked item ${pickedList.distinct()}")
                    pickedList.add(item)

                }
            }
            Log.d("PickedFragment" , "picked list ${pickedList.distinct()}")
            binding.pickedRecyclerView.layoutManager = LinearLayoutManager(context)
            binding.pickedRecyclerView.adapter = PickedListAdapter(pickedList.distinct() ,roomViewModel)
            pickedList.clear()

        })


    }
}