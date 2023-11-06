package com.beren.capstone_project.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beren.capstone_project.entity.Products
import com.beren.capstone_project.repository.ProductsDaoRepository

class CampaignsPageFragmentViewModel:ViewModel() {
    var campaignsItemList= MutableLiveData<List<Products>>()
    val pdaor= ProductsDaoRepository()
    var success= MutableLiveData<Int>()


    init{
        fetchDiscountProducts()
        campaignsItemList=pdaor.fetchDiscountItems()
        success=pdaor.discountSuccess()
    }

    fun fetchDiscountProducts(){
        pdaor.fetchDiscountProducts()
    }
    fun updateDiscount(){
        pdaor.updateCampaign(19,0)
    }
}