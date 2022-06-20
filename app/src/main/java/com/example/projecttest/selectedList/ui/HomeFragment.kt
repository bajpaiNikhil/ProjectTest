package com.example.projecttest.selectedList.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projecttest.R
import com.example.projecttest.dataBase.db.ItemProductDatabase
import com.example.projecttest.dataBase.entities.ItemProduct
import com.example.projecttest.dataBase.repository.ItemProductRepository
import com.example.projecttest.dataBase.viewModel.ItemProductViewModel
import com.example.projecttest.dataBase.viewModel.ItemProductViewModelFactory
import com.example.projecttest.databinding.FragmentHomeBinding
import com.example.projecttest.selectedList.adapter.SelectedListAdapter


class homeFragment : Fragment() {

    private lateinit var selectedList : MutableList<ItemProduct>
    private lateinit var recyclerView: RecyclerView

    private var _binding : FragmentHomeBinding? = null
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
        //return inflater.inflate(R.layout.fragment_home, container, false)
        _binding = FragmentHomeBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectedList = mutableListOf()
        setupViewModel()
    }

    private fun setupViewModel() {

        val roomViewModel = ViewModelProvider(this , ItemProductViewModelFactory(repository = ItemProductRepository(
            ItemProductDatabase(context!!)
        )
        )
        )[ItemProductViewModel::class.java]

        roomViewModel.getAllItemProductList().observe(viewLifecycleOwner , Observer {
            for (item in it){
                if (item.selectedList){
                    binding.selectedListAnimation.visibility = View.INVISIBLE
                    selectedList.add(item)
                }
            }

            binding.selectedRecyclerView.layoutManager = LinearLayoutManager(context)
            binding.selectedRecyclerView.adapter?.notifyDataSetChanged()
            binding.selectedRecyclerView.adapter = SelectedListAdapter(selectedList.distinct() , roomViewModel)
            selectedList.clear()
            Log.d("HomeFragment" , "this is the selected list ${selectedList.distinct()}")

        })
    }
}