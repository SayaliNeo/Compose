package com.example.neospacecompose.repository.daoRepo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.neospacecompose.model.Note
import com.example.neospacecompose.model.Products
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

@Dao
interface ProductDao {

   /* @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertProduct(product: MutableStateFlow<List<Products>>)*/
/*   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertProduct(products: MutableStateFlow<List<Products>>)*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(products: Products) /*:MutableStateFlow<List<Products>>*/

    @Query("DELETE FROM products WHERE id = :id")
    fun deleteProduct(id: Int)

   /* @Query("SELECT * FROM products")
    suspend fun getAllProducts(): List<Products>*/

    @Query("SELECT * from products")
    fun getAllProducts(): Flow<List<Products>>

}