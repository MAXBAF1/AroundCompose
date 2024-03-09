package com.example.aroundcompose.screens.splash.permission

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
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
import com.example.aroundcompose.screens.splash.permission.models.PermissionsEvent
import com.example.aroundcompose.screens.splash.permission.models.PermissionsViewState
import com.example.aroundcompose.screens.splash.permission.views.PermissionsBottomSheet
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PermissionsScreen(
    viewModel: PermissionsViewModel,
    onBackPressed: () -> Unit,
    onPermissionsGranted: () -> Unit,
) {
    val viewState by viewModel.getViewState().collectAsState()
    val lifecycleState by LocalLifecycleOwner.current.lifecycle.currentStateFlow.collectAsState()
    val context = LocalContext.current

    val checkPermission = {
        checkPermission(context = context,
            onGranted = onPermissionsGranted,
            onNotGranted = { viewModel.obtainEvent(PermissionsEvent.NotGranted) })
    }
    when (viewState) {
        PermissionsViewState.CheckGranted -> checkPermission()

        PermissionsViewState.NotGranted -> {
            showPermissionDialog(onAccept = onPermissionsGranted,
                onDismiss = { viewModel.obtainEvent(PermissionsEvent.DismissPermission) })
        }

        PermissionsViewState.PermissionDismissed -> {
            PermissionsBottomSheet(onBackPressed = onBackPressed, onOpenAppSettings = {
                openAppSettings(context)
                if (lifecycleState == Lifecycle.State.RESUMED) checkPermission()
            })
        }
    }
}

private fun checkPermission(
    context: Context,
    onGranted: () -> Unit,
    onNotGranted: () -> Unit,
) {
    val granted = ContextCompat.checkSelfPermission(
        context, Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    if (granted) onGranted() else onNotGranted()
}

private fun openAppSettings(context: Context) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    val uri = Uri.fromParts("package", context.packageName, null)
    intent.data = uri
    context.startActivity(intent)
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun showPermissionDialog(onAccept: () -> Unit, onDismiss: () -> Unit) {
    val launcher = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION) {
        if (it) onAccept() else onDismiss()
    }

    SideEffect { launcher.launchPermissionRequest() }
}