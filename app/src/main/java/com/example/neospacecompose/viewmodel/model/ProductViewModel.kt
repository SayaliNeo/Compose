package com.example.neospacecompose.viewmodel.model

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neospacecompose.model.ProductData
import com.example.neospacecompose.model.Products
import com.example.neospacecompose.repository.APIService
import com.example.neospacecompose.repository.ProductRepository
import com.example.neospacecompose.repository.room.RoomDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(val repository: ProductRepository) : ViewModel() {
    //var prodList = mutableStateListOf<Products>()
    var errorMessage: String by mutableStateOf("")
    private var productList = MutableStateFlow<List<Products>>(emptyList())

    val prodList = productList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllProducts().distinctUntilChanged().collect { listOfProducts ->
                if (listOfProducts.isNullOrEmpty()) {
                    Log.d("Empty", ": Empty List")
                    allProductList()
                } else {
                    productList.value = listOfProducts
                }
            }
        }
    }

    fun allProductList() {
        viewModelScope.launch(Dispatchers.IO) {
            val apiService = APIService.getInstance()

            Log.d("API Call ", apiService.getProductList().toString())
            try {
                repository.insertProduct(apiService.getProductList())
                productList = repository.getAllProducts() as MutableStateFlow<List<Products>>

            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }


        }
    }
}