package com.beren.capstone_project.repository

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.beren.capstone_project.entity.CRUDResponse
import com.beren.capstone_project.entity.Products
import com.beren.capstone_project.entity.Users
import com.beren.capstone_project.entity.UsersResponse
import com.beren.capstone_project.retrofit.ApiUtils
import com.beren.capstone_project.retrofit.ProductsDaoInterface
import com.beren.capstone_project.retrofit.UsersDaoInterface
import com.beren.capstone_project.viewmodel.LoginFragmentViewModel
import com.beren.capstone_project.viewmodel.SignupFragmentViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersDaoRepository {
    private val usersList: MutableLiveData<List<Users>>
    private val udaoi: UsersDaoInterface
    private var loginValidate= MutableLiveData<Int>()
    private var registerSuccess= MutableLiveData<Int>()

    private val userInfo:MutableLiveData<List<Users>>

    init {
        udaoi = ApiUtils.getUsersDaoInterface()
        usersList = MutableLiveData()
        loginValidate=MutableLiveData()
        userInfo= MutableLiveData()


    }

    fun fetchUsers(): MutableLiveData<List<Users>> {
        return usersList
    }
    fun loginSuccess():MutableLiveData<Int>{
        return loginValidate
    }
    fun registerSuccess():MutableLiveData<Int>{
        return registerSuccess
    }
    fun userInfoList():MutableLiveData<List<Users>>{
        return userInfo
    }

    fun register(user_email:String,user_name:String,user_password:String,user_phone:String) {

        udaoi.create_user(user_email, user_name, user_password, user_phone)
                .enqueue(object : Callback<CRUDResponse> {
                    override fun onResponse(call: Call<CRUDResponse>?, response: Response<CRUDResponse>) {
                        registerSuccess.value=response.body().success
                    }
                    override fun onFailure(call: Call<CRUDResponse>?, t: Throwable?) {}

                })
    }

    fun login(user_email:String, user_password:String) {
        var arrayList=arrayListOf<Users>()
        udaoi.login(user_email, user_password).enqueue(object : Callback<UsersResponse> {
            override fun onResponse(call: Call<UsersResponse>?, response: Response<UsersResponse>) {
                val user = response.body().users
                for (i in user) {
                       loginValidate.value=i.deger
                    usersList.value = user
                    arrayList.add(i)
                       /* val bundle=Bundle()
                        val context=this@UsersDaoRepository
                        val intent=Intent(this@UsersDaoRepository,LoginFragmentViewModel::class.java)
                        bundle.putString("success", "1")
                        intent.putExtras(bundle)
                        startActivity(intent)*/
                }
                userInfo.value=arrayList
            }
            override fun onFailure(call: Call<UsersResponse>?, t: Throwable?) {
                Log.e("8","hata db kaynaklı kullanıcı bulunamadı")
            }
        })
    }

}