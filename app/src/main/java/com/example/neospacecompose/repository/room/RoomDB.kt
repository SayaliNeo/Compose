package com.example.neospacecompose.repository.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.neospacecompose.model.Note
import com.example.neospacecompose.model.Products
import com.example.neospacecompose.repository.daoRepo.NoteDBDao
import com.example.neospacecompose.repository.daoRepo.ProductDao

@Database(entities = [Products::class, Note::class], version = 2, exportSchema = false)
abstract class RoomDB : RoomDatabase() {
    abstract fun noteDao(): NoteDBDao
    abstract fun productDao(): ProductDao

    /*companion object {
        private var INSTANCE: RoomDB? = null

        fun getInstance(context: Context): RoomDB {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RoomDB::class.java,
                        "RoomDB"
                    ).fallbackToDestructiveMigration().build()
                }
                return instance
            }
        }
    }*/
}