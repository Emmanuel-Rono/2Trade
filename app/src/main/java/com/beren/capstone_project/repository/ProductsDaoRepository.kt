package com.beren.capstone_project.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.beren.capstone_project.entity.CRUDResponse
import com.beren.capstone_project.entity.Products
import com.beren.capstone_project.entity.ProductsResponse
import com.beren.capstone_project.retrofit.ApiUtils
import com.beren.capstone_project.retrofit.ProductsDaoInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsDaoRepository {
    private val productsList:MutableLiveData<List<Products>>
    private val pdaoi:ProductsDaoInterface
    private var cartValidate= MutableLiveData<Int>()
    private var discountValidate= MutableLiveData<Int>()
    private val cartProductsList:MutableLiveData<List<Products>>
    private val discountProductsList:MutableLiveData<List<Products>>
    private var cartCountno= MutableLiveData<Int>()
    private val totalPayment : MutableLiveData<Double>

    init {
        pdaoi=ApiUtils.getProductsDaoInterface()
        productsList= MutableLiveData()
        cartProductsList= MutableLiveData()
        discountProductsList=MutableLiveData()
        cartValidate=MutableLiveData()
        discountValidate=MutableLiveData()
        totalPayment= MutableLiveData()
        totalPayment.value=0.0


    }
    fun fetchProducts():MutableLiveData<List<Products>>{
        return productsList
    }
    fun fetchCartItems():MutableLiveData<List<Products>>{
        return cartProductsList
    }
    fun fetchDiscountItems():MutableLiveData<List<Products>>{
        return discountProductsList
    }
    fun calculateTotalPayment():MutableLiveData<Double>{
        return totalPayment
    }
    fun createProduct(ownerName:String,productName:String,productPrice:String,productDescription:String,productImageUrl:String,discountStatus:Int,cartStatus:Int,productId:Int){
        pdaoi.createProduct(ownerName,productName,productPrice,productDescription,productImageUrl,discountStatus,cartStatus,productId)
            .enqueue(object : Callback<CRUDResponse> {
                override fun onResponse(call: Call<CRUDResponse>?, response: Response<CRUDResponse>) {
                }
                override fun onFailure(call: Call<CRUDResponse>?, t: Throwable?) {
                }
            })
    }
    fun getProducts(ownerName:String) {
        pdaoi.getProducts(ownerName).enqueue(object : Callback<ProductsResponse> {
            override fun onResponse(call: Call<ProductsResponse>?, response: Response<ProductsResponse>) {
                val list = response.body().products
                productsList.value = list
            }
            override fun onFailure(call: Call<ProductsResponse>?, t: Throwable?) {
            }
        })
    }
    fun updateCart(id:Int,cart_status:Int){
        pdaoi.updateCart(id,cart_status).enqueue(object : Callback<CRUDResponse>{
            override fun onResponse(call: Call<CRUDResponse>?, response: Response<CRUDResponse>) {
                cartValidate.value=response.body().success
            }
            override fun onFailure(call: Call<CRUDResponse>?, t: Throwable?) {}
        })
    }
    fun cartSuccess():MutableLiveData<Int>{
        return cartValidate
    }
    fun discountSuccess():MutableLiveData<Int>{
        return discountValidate
    }
    fun cartCount():MutableLiveData<Int>{
        return cartCountno
    }

    fun fetchCartProducts() {
        pdaoi.getProducts("berenkotanli").enqueue(object : Callback<ProductsResponse> {
            override fun onResponse(call: Call<ProductsResponse>?, response: Response<ProductsResponse>) {
                val list = response.body().products
                var total=0.0
                var arrayList=arrayListOf<Products>()
                var priceList=arrayListOf<Double>()
                for (p in list){
                    Log.e("************","************")

                    Log.e("1",p.urun_adi)
                    Log.e("7",p.id.toString())
                    Log.e("2",p.urun_aciklama)
                    Log.e("3",p.urun_fiyat)
                    Log.e("4",p.urun_gorsel_url)
                    Log.e("5",p.urun_indirimli_mi.toString())
                    Log.e("6",p.sepet_durum.toString())
                    if (p.sepet_durum==1){
                        arrayList.add(p)
                        //priceList.add(p.urun_fiyat.toDouble())
                        total += p.urun_fiyat.toDouble()

                    }
                }
                cartProductsList.value=arrayList
                cartCountno.value=arrayList.size
                totalPayment.value=total

                }
            override fun onFailure(call: Call<ProductsResponse>?, t: Throwable?) {
            }
        })
    }
    fun updateCampaign(id:Int,cart_status:Int){
        pdaoi.updateCampaign(id,cart_status).enqueue(object : Callback<CRUDResponse>{
            override fun onResponse(call: Call<CRUDResponse>?, response: Response<CRUDResponse>) {
                discountValidate.value=response.body().success
            }
            override fun onFailure(call: Call<CRUDResponse>?, t: Throwable?) {}
        })
    }
    fun fetchDiscountProducts() {
        pdaoi.getProducts("berenkotanli").enqueue(object : Callback<ProductsResponse> {
            override fun onResponse(call: Call<ProductsResponse>?, response: Response<ProductsResponse>) {
                val list = response.body().products
                var arrayListDiscount=arrayListOf<Products>()
                for (p in list){
                    Log.e("************","************")

                    Log.e("1",p.urun_adi)
                    Log.e("7",p.id.toString())
                    Log.e("2",p.urun_aciklama)
                    Log.e("3",p.urun_fiyat)
                    Log.e("4",p.urun_gorsel_url)
                    Log.e("5",p.urun_indirimli_mi.toString())
                    Log.e("6",p.sepet_durum.toString())
                    if (p.urun_indirimli_mi==1){
                        arrayListDiscount.add(p)
                    }
                }
                discountProductsList.value=arrayListDiscount
            }
            override fun onFailure(call: Call<ProductsResponse>?, t: Throwable?) {
            }
        })
}}