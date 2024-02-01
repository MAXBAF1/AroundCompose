package com.example.aroundcompose.di

import android.app.Service
import android.content.Context
import android.content.SharedPreferences
import com.example.aroundcompose.screens.map.location_service.LocationService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
object SharedPrefModule {
    @Provides
    fun provideSharedPref(@ApplicationContext context: Context) : SharedPreferences {
        return context.getSharedPreferences(LocationService.S_PREF_LOCATION, Service.MODE_PRIVATE)
    }
}