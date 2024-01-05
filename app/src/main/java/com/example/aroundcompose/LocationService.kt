package com.example.aroundcompose

import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.mapbox.geojson.Point
import kotlinx.coroutines.tasks.await
import android.Manifest
import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Looper
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest

class LocationService(private val context: Context) : Service() {
    private val locationManager =
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    private val locationProvider = LocationServices.getFusedLocationProviderClient(context)

    private fun checkPermissions(): Boolean {
        when {
            !locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) -> throw LocationServiceException.LocationDisabledException()
            !locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) -> throw LocationServiceException.NoNetworkEnabledException()
            (ActivityCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED) -> throw LocationServiceException.MissingPermissionException()

            else -> return true
        }
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        if (!checkPermissions()) return

        val request =
            LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, updateInterval).build()

        locationProvider.requestLocationUpdates(request, locCallback, Looper.myLooper())
    }


    sealed class LocationServiceException : Exception() {
        class MissingPermissionException : LocationServiceException()
        class LocationDisabledException : LocationServiceException()
        class NoNetworkEnabledException : LocationServiceException()
        class UnknownException(val str: String) : LocationServiceException()
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //Notification(this).startNotification()
        isRunning = true
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        isRunning = false
        locationProvider.removeLocationUpdates(locCallback)
    }

    companion object {
        lateinit var locCallback: LocationCallback
        const val CHANNEL_ID = "channel_1"
        val updateInterval = 100L
        var isRunning = false
    }
}