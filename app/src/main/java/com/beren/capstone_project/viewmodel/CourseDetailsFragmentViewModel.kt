package com.beren.capstone_project.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beren.capstone_project.repository.ProductsDaoRepository


class CourseDetailsFragmentViewModel:ViewModel() {
    val pdaor= ProductsDaoRepository()
    var success= MutableLiveData<Int>()
    init {
        success=pdaor.cartSuccess()
    }

fun updateCart(id:Int,cartStatus:Int){
    pdaor.updateCart(id,cartStatus)
}
}
