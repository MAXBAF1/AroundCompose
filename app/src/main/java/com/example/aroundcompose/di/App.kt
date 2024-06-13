package com.example.aroundcompose.di

import android.app.Application
import com.example.aroundcompose.data.db.AroundDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    val database by lazy { AroundDatabase.getDatabase(this) }
}