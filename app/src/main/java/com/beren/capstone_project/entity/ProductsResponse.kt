package com.beren.capstone_project.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProductsResponse(@SerializedName("urunler")
                            @Expose
                            var products:List<Products>,
                            @SerializedName("success")
                            @Expose
                            var success:Int) {
}