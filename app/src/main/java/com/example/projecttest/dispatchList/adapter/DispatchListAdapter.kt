package com.example.projecttest.dispatchList.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projecttest.R
import com.example.projecttest.dataBase.entities.ItemProduct
import com.example.projecttest.dataBase.viewModel.ItemProductViewModel
import com.example.projecttest.databinding.DispatchItemBinding

class DispatchListAdapter(
    private val productList: List<ItemProduct>,
    private val roomViewModel : ItemProductViewModel
) : RecyclerView.Adapter<DispatchListAdapter.ItemViewHolder>() {


    inner class ItemViewHolder(var binding : DispatchItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =  DispatchItemBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        with(holder){
            with(productList[position]){
                binding.productItemTitleTv.text  = this.name
                binding.productItemAmtTv.text = this.productAmount.toString()
                Glide.with(itemView).load(this.productImage).into(binding.productItemIv)
            }
        }


        holder.binding.RadioGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.selectedRb -> {
                    productList[position].selectedList = true
                    productList[position].dispatchList = false
                    productList[position].pickedList = false
                    roomViewModel.upsert(productList[position])
                }
                R.id.pickedRb ->{
                    productList[position].selectedList = false
                    productList[position].dispatchList = false
                    productList[position].pickedList = true

                    Log.d("selectedAdapter" , "picked Clicked")
                    roomViewModel.upsert(productList[position])
                }
            }
        })



    }


    override fun getItemCount(): Int {
        return productList.size
    }
}