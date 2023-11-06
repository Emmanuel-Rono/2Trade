package com.beren.capstone_project.retrofit

import com.beren.capstone_project.entity.CRUDResponse
import com.beren.capstone_project.entity.UsersResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface UsersDaoInterface {
    @GET("giris_yap.php")
    fun users():Call<UsersResponse>

    @POST("uye_ol.php")
    @FormUrlEncoded
    fun create_user(@Field("mail_adres")mail_adres:String,
                    @Field("ad_soyad")ad_soyad:String,
                    @Field("sifre") sifre:String,
                    @Field("telefon")telefon:String):Call<CRUDResponse>

    @POST("giris_yap.php")
    @FormUrlEncoded
    fun login(@Field("mail_adres")mail_adres:String,
              @Field("sifre")sifre:String):Call<UsersResponse>




}