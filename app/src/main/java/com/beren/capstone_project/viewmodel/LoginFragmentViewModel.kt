package com.beren.capstone_project.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beren.capstone_project.entity.Users
import com.beren.capstone_project.repository.UsersDaoRepository

class LoginFragmentViewModel: ViewModel() {
    val udaor = UsersDaoRepository()
    var success = MutableLiveData<Int>()
    var user = MutableLiveData<List<Users>>()

    init {
        success = udaor.loginSuccess()
        user=udaor.userInfoList()
    }

    fun login(user_email: String, user_password: String) {
        udaor.login(user_email, user_password)
    }
}