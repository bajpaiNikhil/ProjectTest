package com.example.projecttest.selectedList.adapter

import android.app.Activity
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projecttest.R
import com.example.projecttest.dataBase.entities.ItemProduct
import com.example.projecttest.dataBase.viewModel.ItemProductViewModel
import com.example.projecttest.databinding.SelectedItemBinding
import com.thecode.aestheticdialogs.AestheticDialog
import com.thecode.aestheticdialogs.DialogAnimation
import com.thecode.aestheticdialogs.DialogStyle
import com.thecode.aestheticdialogs.DialogType


class SelectedListAdapter(
    private val productList: List<ItemProduct> ,
    private val roomViewModel : ItemProductViewModel
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


        holder.binding.RadioGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.dispatchRb -> {
                    productList[position].selectedList = false
                    productList[position].dispatchList = true
                    productList[position].pickedList = false

                    //roomViewModel.upsert(productList[position])
                    AestheticDialog.Builder(holder.itemView.context as Activity , DialogStyle.TOASTER , DialogType.SUCCESS)
                        .setTitle("Dispatch State")
                        .setMessage("Selected item is moved to dispatch state")
                        .setCancelable(true)
                        .setDarkMode(true)
                        .setAnimation(DialogAnimation.SLIDE_RIGHT)
                        .show()
                    roomViewModel.upsert(productList[position])
                }
                R.id.pickedRb ->{
                    productList[position].selectedList = false
                    productList[position].dispatchList = false
                    productList[position].pickedList = true
                    Log.d("selectedAdapter" , "picked Clicked")
                    //roomViewModel.upsert(productList[position])
                    AestheticDialog.Builder(holder.itemView.context as Activity , DialogStyle.TOASTER , DialogType.SUCCESS)
                        .setTitle("Picked State")
                        .setMessage("Selected item is moved to picked state")
                        .setCancelable(true)
                        .setDarkMode(true)
                        .setAnimation(DialogAnimation.SLIDE_RIGHT)
                        .show()
                    roomViewModel.upsert(productList[position])
                }
            }
        })



    }


    override fun getItemCount(): Int {
        return productList.size
    }
}