package com.example.aroundcompose.di

import android.content.Context
import androidx.room.Room
import com.example.aroundcompose.data.db.AroundDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
//    @Volatile
//    private var Instance: AroundDatabase? = null

//    @Singleton
//    @Provides
//    fun provideDatabase(@ApplicationContext context: Context): AroundDatabase {
//        return Instance ?: synchronized(this) {
//        Room
//            .databaseBuilder(context, AroundDatabase::class.java, "around_database")
//            .build()
//                .also { Instance = it }
//    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AroundDatabase {
        return Room
            .databaseBuilder(context, AroundDatabase::class.java, "around_database")
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(db: AroundDatabase) = db.dao()
}