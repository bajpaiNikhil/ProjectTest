package com.example.projecttest.selectedList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.RadioButton;
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projecttest.R
import com.example.projecttest.dataBase.entities.ItemProduct
import com.example.projecttest.databinding.SelectedItemBinding


class SelectedListAdapter(
    private val productList: List<ItemProduct>,
) : RecyclerView.Adapter<SelectedListAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(var binding : SelectedItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =  SelectedItemBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        //val currentItem = productList[position]
        with(holder){
            with(productList[position]){
                binding.productItemTitleTv.text  = this.name
                binding.productItemAmtTv.text = this.productAmount.toString()
                Glide.with(itemView).load(this.productImage).into(binding.productItemIv)
            }
        }

        //holder.binding.RadioGroup.setOnCheckedChangeListener { radioGroup, i ->  }


        holder.binding.RadioGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.dispatchRb -> {
                    productList[position].selectedList = false
                    productList[position].dispatchList = true
                    productList[position].pickedList = false

                }
                R.id.pickedRb ->{

                    productList[position].selectedList = false
                    productList[position].dispatchList = false
                    productList[position].pickedList = true

                }
            }
        })



    }


    override fun getItemCount(): Int {
        return productList.size
    }
}