package com.example.neospacecompose.viewmodel.model

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neospacecompose.model.ProductData
import com.example.neospacecompose.model.Products
import com.example.neospacecompose.model.UserDetails
import com.example.neospacecompose.repository.APIService
import kotlinx.coroutines.launch
import retrofit2.Call

class ProductViewModel : ViewModel() {
    private val productList = ArrayList<ProductData>()
    var errorMessage: String by mutableStateOf("")

    val prodList: ArrayList<ProductData>
        get() = productList

    fun allProductList() {
        viewModelScope.launch {
            val apiService = APIService.getInstance()

            try {
                productList.clear()
                productList.add(apiService.getProductList())

            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }


        }
    }
}