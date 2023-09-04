package com.example.neospacecompose.di

import android.content.Context
import androidx.room.Room
import com.example.neospacecompose.repository.room.RoomDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    // You can use Retrofit for network calls
    @Singleton
    @Provides
    fun provideNoteDao(database: RoomDB) = database.noteDao()

    @Singleton
    @Provides
    fun provideProductDao(database: RoomDB) = database.productDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): RoomDB =
        Room.databaseBuilder(context, RoomDB::class.java, "RoomDB")
            .fallbackToDestructiveMigration().build()


}