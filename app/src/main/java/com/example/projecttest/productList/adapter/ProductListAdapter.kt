package com.example.projecttest.productList.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projecttest.MainActivity
import com.example.projecttest.R
import com.example.projecttest.dataBase.entities.ItemProduct
import com.example.projecttest.dataBase.viewModel.ItemProductViewModel
import com.example.projecttest.databinding.ProductItemBinding
import com.example.projecttest.productList.model.ProductItem
import com.thecode.aestheticdialogs.*
import kotlin.properties.Delegates

class ProductListAdapter(private val productList : List<ProductItem> ,
                         private val roomViewModel : ItemProductViewModel
                         ) : RecyclerView.Adapter<ProductListAdapter.ItemViewHolder>() {
    private var selectedCb : Boolean = false
    private var dispatch: Boolean = false
    private var picked : Boolean = false
    private lateinit var item : ItemProduct
    private var flag : Boolean = false
    inner class ItemViewHolder(val binding : ProductItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =  ProductItemBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, @SuppressLint("RecyclerView") position: Int) {
        with(holder){
            with(productList[position]){
                binding.productItemTitleTv.text  = this.title
                binding.productItemAmtTv.text = this.price.toString()
                Glide.with(itemView).load(this.image).into(binding.productItemIv)
            }
        }
        holder.binding.selectedBtn.setOnClickListener {
            AestheticDialog.Builder(holder.itemView.context as Activity, DialogStyle.FLAT, DialogType.SUCCESS)
                .setTitle("ORDER")
                .setMessage("Do you want to order this item")
                .setCancelable(true)
                .setDarkMode(true)
                .setGravity(Gravity.CENTER)
                .setAnimation(DialogAnimation.SHRINK)
                .setOnClickListener(object : OnDialogClickListener {
                    override fun onClick(dialog: AestheticDialog.Builder) {
                        selectedCb = true
                        dispatch = false
                        picked = false
                        item = ItemProduct(
                                productList[position].title ,
                                productList[position].price.toInt() ,
                                productList[position].image ,
                                selectedCb ,
                                dispatch ,
                                picked
                        )
                        roomViewModel.upsert(item)
                        flag = true
                        dialog.dismiss()
                    }
                })
                .show()


        }

//        holder.binding.selectedCb.setOnClickListener { v ->
//            if ((v as CheckBox).isChecked) {
//                selectedCb = true
//                dispatch = false
//                picked = false
//                item = ItemProduct(
//                    productList[position].title ,
//                    productList[position].price.toInt() ,
//                    productList[position].image ,
//                    selectedCb ,
//                    dispatch ,
//                    picked
//                )
//
//                roomViewModel.upsert(item)
//
//                Log.d("ProductAdapter" , "Value if Check")
//            }else{
//                roomViewModel.delete(item) // this is not working no idea why .
//                Log.d("ProductAdapter" , "Value if UnCheck ")
//            }
//        }


    }


    override fun getItemCount(): Int {
        return productList.size
    }
}