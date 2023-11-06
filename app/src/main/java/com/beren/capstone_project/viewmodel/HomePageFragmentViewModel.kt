package com.beren.capstone_project.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beren.capstone_project.entity.Products
import com.beren.capstone_project.repository.ProductsDaoRepository

class HomePageFragmentViewModel: ViewModel() {
    var productsList= MutableLiveData<List<Products>>()
    val pdaor=ProductsDaoRepository()
    var cartCount= MutableLiveData<Int>()
    init{
        fetchProducts("berenkotanli")
        productsList=pdaor.fetchProducts()
        fetchCartProducts()
        cartCount=pdaor.cartCount()
    }

    private fun fetchProducts(ownerName:String) {
       pdaor.getProducts(ownerName)
    }
    fun createProduct(ownerName:String,productName:String,productPrice:String,productDescription:String,productImageUrl:String,discountStatus:Int,cartStatus:Int,productId:Int){
       pdaor.createProduct(ownerName,productName,productPrice,productDescription,productImageUrl,discountStatus,cartStatus,productId)
    }
    fun fetchCartProducts(){
        pdaor.fetchCartProducts()
    }
    fun updateCart(id:Int,cartStatus:Int){
        pdaor.updateCart(id,cartStatus)
        fetchCartProducts()
        cartCount=pdaor.cartCount()
    }
}