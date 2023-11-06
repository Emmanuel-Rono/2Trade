package com.beren.capstone_project.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beren.capstone_project.entity.Products
import com.beren.capstone_project.repository.ProductsDaoRepository

class CartPageFragmentViewModel: ViewModel() {
    var cartItemsList= MutableLiveData<List<Products>>()
    val pdaor=ProductsDaoRepository()
    var success= MutableLiveData<Int>()
    var totalPayment:MutableLiveData<Double>



    init{
        fetchCartProducts()
        cartItemsList=pdaor.fetchCartItems()
        success=pdaor.cartSuccess()
        totalPayment=pdaor.calculateTotalPayment()
    }

    fun fetchCartProducts(){
        pdaor.fetchCartProducts()
    }
    fun deleteCartItem(id:Int,cartStatus:Int){
        pdaor.updateCart(id,cartStatus)
        fetchCartProducts()
    }

}