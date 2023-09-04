package com.example.neospacecompose.repository

import com.example.neospacecompose.model.Note
import com.example.neospacecompose.model.Products
import com.example.neospacecompose.repository.daoRepo.NoteDBDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDBDao: NoteDBDao) {

    suspend fun addNote(note: Note) = noteDBDao.insertData(note = note)
    suspend fun updateNote(note: Note) = noteDBDao.updateData(note)
    suspend fun deleteNote(note: Note) = noteDBDao.deleteNote(note)
    suspend fun deleteAllNote() = noteDBDao.deleteAll()
    fun getAllNotes(): Flow<List<Note>> = noteDBDao.getAllNotes().flowOn(Dispatchers.IO).conflate()
    //suspend fun getAllProducts(products: Products) = noteDBDao.getAllProducts(products)


}