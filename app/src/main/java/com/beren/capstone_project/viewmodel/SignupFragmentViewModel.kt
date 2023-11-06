package com.beren.capstone_project.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beren.capstone_project.repository.UsersDaoRepository

class SignupFragmentViewModel : ViewModel() {
    val udaor=UsersDaoRepository()
    var success = MutableLiveData<Int>()
    init {
        success = udaor.registerSuccess()

    }
    fun register(user_name:String, user_email:String, user_phone:String,user_password:String){
        udaor.register(user_name, user_email, user_phone,user_password)

    }
}