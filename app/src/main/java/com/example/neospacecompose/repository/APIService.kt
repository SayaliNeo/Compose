package com.example.neospacecompose.repository

import com.example.neospacecompose.model.ProductData
import com.example.neospacecompose.model.Products
import com.example.neospacecompose.model.UserDetails
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.Flow


const val Base_URL = "https://dummyjson.com/"

interface APIService {

    @GET("todos")
    suspend fun getLisDetails():List<UserDetails>

    @GET("products")
    suspend fun getProductList(): Products



    companion object {
        var apiService: APIService? = null
        fun getInstance(): APIService {
            if (apiService == null) {
                apiService = Retrofit.Builder().baseUrl(Base_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build()
                    .create(APIService::class.java)
            }

            return apiService!!
        }
    }
}