package com.example.aroundcompose.di

import android.app.Service
import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.aroundcompose.ui.screens.map.location_service.LocationService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Qualifier

@Module
@InstallIn(ViewModelComponent::class)
object SharedPrefModule {
    @Provides
    @NotEncryptedSharedPref
    fun provideSharedPreferences(@ApplicationContext context: Context) : SharedPreferences {
        return context.getSharedPreferences(LocationService.S_PREF_LOCATION, Service.MODE_PRIVATE)
    }

    @Provides
    @EncryptedSharedPref
    fun provideEncryptedSharedPreferences(@ApplicationContext context: Context) : SharedPreferences {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        return EncryptedSharedPreferences.create(
            context,
            "encrypted_shared_prefs",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NotEncryptedSharedPref

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class EncryptedSharedPref