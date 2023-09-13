package com.example.neospacecompose.repository

import androidx.lifecycle.MutableLiveData
import com.example.neospacecompose.model.ProductData
import com.example.neospacecompose.model.Products
import com.example.neospacecompose.repository.daoRepo.ProductDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductRepository @Inject constructor(val productDao: ProductDao) {
    val searchResults = MutableLiveData<List<Products>>()

    val coroutineScope = CoroutineScope(Dispatchers.Main)
    val apiService = APIService.getInstance()


    suspend fun getAllProductsFromApi(): ProductData = apiService.getProductList()

    fun insertProduct(productList: List<Products>) = productDao.insertProduct(productList)
    fun getAllProducts(): Flow<List<Products>> = productDao.getAllProducts().flowOn(Dispatchers.IO).conflate()

    fun allProducts(){
        coroutineScope.launch(Dispatchers.IO) {

            productDao.getAllProducts()
        }
    }


}