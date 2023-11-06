package com.beren.capstone_project.retrofit

import com.beren.capstone_project.entity.CRUDResponse
import com.beren.capstone_project.entity.ProductsResponse
import com.beren.capstone_project.entity.UsersResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductsDaoInterface {
    @POST("urun_ekle.php")
    @FormUrlEncoded
    fun createProduct(@Field("satici_adi")satici_adi:String,
                       @Field("urun_adi")urun_adi:String,
                       @Field("urun_fiyat")urun_fiyat:String,
                       @Field("urun_aciklama")urun_aciklama:String,
                       @Field("urun_gorsel_url")urun_gorsel_url:String,
                       @Field("urun_indirimli_mi")urun_indirimli_mi:Int,
                       @Field("sepet_durum")sepet_durum:Int,
                       @Field("id")id:Int, ): Call<CRUDResponse>

    @POST("urunler.php")
    @FormUrlEncoded
    fun getProducts(@Field("satici_adi")satici_adi:String): Call<ProductsResponse>

    @POST("sepet_durum_degistir.php")
    @FormUrlEncoded
    fun updateCart(@Field("id")id:Int,@Field("sepet_durum")cart_status:Int):Call<CRUDResponse>

    @POST("indirimli_urun_durum_degistir.php")
    @FormUrlEncoded
    fun updateCampaign(@Field("id")id:Int,@Field("urun_indirimli_mi")discount:Int):Call<CRUDResponse>


}