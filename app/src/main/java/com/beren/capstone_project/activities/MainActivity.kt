package com.beren.capstone_project.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.beren.capstone_project.retrofit.ProductsDaoInterface
import com.emmanuel_rono.a2trade.R

class MainActivity : AppCompatActivity() {

    private lateinit var pdaoi: ProductsDaoInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}