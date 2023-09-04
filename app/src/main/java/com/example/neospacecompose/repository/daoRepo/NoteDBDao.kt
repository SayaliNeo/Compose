package com.example.neospacecompose.repository.daoRepo

import androidx.compose.runtime.MutableState
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.neospacecompose.model.Note
import com.example.neospacecompose.model.Products
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDBDao {

    @Query("SELECT * from notes_tbl")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * from notes_tbl where id=:id")
    suspend fun getNoteById(id: String): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
   suspend fun updateData(note: Note)

  /*  @Delete
    suspend fun deleteById(id:String)*/

    @Query("DELETE from notes_tbl")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteNote(note: Note)

  /*  @Query("SELECT * from products")
    suspend fun getAllProducts(products: Products):Products*/
}