package com.example.neospacecompose.model

import com.google.gson.annotations.SerializedName

data class ProductData(@SerializedName("products") var products: ArrayList<Products> = arrayListOf())