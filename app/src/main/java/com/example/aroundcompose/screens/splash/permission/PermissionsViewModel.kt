package com.example.aroundcompose.screens.splash.permission

import android.Manifest
import android.app.Application
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import com.example.aroundcompose.domain.BaseViewModel
import com.example.aroundcompose.screens.splash.permission.models.PermissionsAction
import com.example.aroundcompose.screens.splash.permission.models.PermissionsEvent
import com.example.aroundcompose.screens.splash.permission.models.PermissionsViewState
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PermissionsViewModel @Inject constructor(application: Application) :
    BaseViewModel<PermissionsViewState, PermissionsAction, PermissionsEvent>(
        application = application, initialAction = PermissionsAction.CheckGranted
    ) {

    @Composable
    override fun composableObtainEvent(viewEvent: PermissionsEvent) {
        when (viewEvent) {
            PermissionsEvent.CheckGranted -> setLocationPermissionState()
            PermissionsEvent.NotGranted -> launchPermissions()
            PermissionsEvent.AppSettings -> openAppSettings()
        }
    }

    @OptIn(ExperimentalPermissionsApi::class)
    @Composable
    private fun launchPermissions() {
        val launcher = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION) {
            viewAction.update { _ ->
                if (it) PermissionsAction.Granted else PermissionsAction.PermissionNotAllowed
            }
        }

        SideEffect { launcher.launchPermissionRequest() }
    }

    @Composable
    private fun openAppSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        val uri = Uri.fromParts("package", context.packageName, null)
        intent.data = uri
        context.startActivity(intent)
        listenOnResume()
    }

    @Composable
    private fun listenOnResume() {
        var checkGranted by remember { mutableStateOf(false) }
        val lifecycleState by LocalLifecycleOwner.current.lifecycle.currentStateFlow.collectAsState()

        LaunchedEffect(lifecycleState) {
            checkGranted = lifecycleState == Lifecycle.State.RESUMED
        }

        if (checkGranted) {
            checkGranted = setLocationPermissionState()
        }
    }

    @Composable
    private fun setLocationPermissionState(): Boolean {
        val isGranted = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        viewAction.update {
            if (isGranted) PermissionsAction.Granted else PermissionsAction.NotGranted
        }

        return isGranted
    }
}