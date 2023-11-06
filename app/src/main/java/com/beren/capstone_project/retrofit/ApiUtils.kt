package com.beren.capstone_project.retrofit

class ApiUtils {

    companion object{

        val BASE_URL = "http://upschool.canerture.com/"

        fun getUsersDaoInterface(): UsersDaoInterface {
            return RetrofitClient.getClient(BASE_URL).create(UsersDaoInterface::class.java)
        }
        fun getProductsDaoInterface(): ProductsDaoInterface {
            return RetrofitClient.getClient(BASE_URL).create(ProductsDaoInterface::class.java)
        }
    }
}