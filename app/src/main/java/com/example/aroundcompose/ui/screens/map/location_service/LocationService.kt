package com.example.aroundcompose.ui.screens.map.location_service

import android.Manifest
import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.IBinder
import android.os.Looper
import androidx.core.app.ActivityCompat
import com.example.aroundcompose.utils.toPoint
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.mapbox.geojson.Point

class LocationService : Service() {
    private val locationManager by lazy {
        baseContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }
    private val locationProvider by lazy {
        LocationServices.getFusedLocationProviderClient(baseContext)
    }

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        startLocationUpdates()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startNotification(this)
        isRunning = true
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()

        val sharedPreferences = getSharedPreferences(S_PREF_LOCATION, MODE_PRIVATE)
        val strPoint = lastLocation?.toJson()
        sharedPreferences.edit().putString(LAST_LOCATION_ID, strPoint).apply()

        isRunning = false
        locationProvider.removeLocationUpdates(onLocationResult)
        //killProcess(myPid())
    }

    private fun checkPermissions(): Boolean {
        when {
            !locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) -> throw LocationServiceException.LocationDisabledException()
            !locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) -> throw LocationServiceException.NoNetworkEnabledException()
            (ActivityCompat.checkSelfPermission(
                baseContext, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                baseContext, Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED) -> throw LocationServiceException.MissingPermissionException()

            else -> return true
        }
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        checkPermissions()

        val request =
            LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, UPDATE_INTERVAL).build()

        locationProvider.requestLocationUpdates(request, locationCallback, Looper.myLooper())
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(lresult: LocationResult) {
            super.onLocationResult(lresult)
            val location = lresult.lastLocation ?: return
            lastLocation = location.toPoint()
            onLocationResult(location)
        }
    }

    @SuppressLint("MissingPermission")
    fun getCurrentLocation() {
        checkPermissions()

        val request =
            CurrentLocationRequest.Builder().setPriority(Priority.PRIORITY_HIGH_ACCURACY).build()

        locationProvider.getCurrentLocation(request, null)
            .addOnSuccessListener { onLocationResult(it) }
    }


    sealed class LocationServiceException : Exception() {
        class MissingPermissionException : LocationServiceException()
        class LocationDisabledException : LocationServiceException()
        class NoNetworkEnabledException : LocationServiceException()
        class UnknownException(val str: String) : LocationServiceException()
    }

    companion object {
        lateinit var onLocationResult: (Location) -> Unit
        var lastLocation: Point? = null
        const val CHANNEL_ID = "channel_1"
        const val NOTIFICATION_ID = 99
        const val S_PREF_LOCATION = "S_PREF_LOCATION"
        const val LAST_LOCATION_ID = "LAST_LOCATION_ID"
        const val UPDATE_INTERVAL = 100L
        var isRunning = false
    }
}