package com.example.aroundcompose.screens

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import com.example.aroundcompose.R

@Composable
fun PermissionsScreen(onPermissionsGranted: () -> Unit) {
    var showBottomSheet by remember { mutableStateOf(false) }

    showPermissionDialog(onPermissionsGranted) { showBottomSheet = true }

    if (showBottomSheet) BottomSheet()

    onResumeScreen(onPermissionsGranted)
}

@Composable
private fun showPermissionDialog(
    onPermissionsGranted: () -> Unit,
    onPermissionsNotGranted: () -> Unit,
) {
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val permissionsGranted = permissions.values.reduce { acc, isPermissionGranted ->
            acc && isPermissionGranted
        }

        if (permissionsGranted) onPermissionsGranted() else onPermissionsNotGranted()
    }
    val permissionList = listOf(Manifest.permission.ACCESS_FINE_LOCATION)
    SideEffect { locationPermissionLauncher.launch(permissionList.toTypedArray()) }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BottomSheet() {
    val sheetState = rememberModalBottomSheetState(confirmValueChange = { it != SheetValue.Hidden })
    val context = LocalContext.current

    ModalBottomSheet(
        onDismissRequest = { }, sheetState = sheetState
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.allow_access_to_location))
            Button(modifier = Modifier.padding(top = 30.dp, bottom = 30.dp), onClick = {
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts("package", context.packageName, null)
                intent.data = uri
                context.startActivity(intent)
            }) {
                Text(text = stringResource(id = R.string.to_settings_bnt))
            }
        }
    }
}

@Composable
private fun onResumeScreen(onPermissionsGranted: () -> Unit) {
    var checkPermission by remember { mutableStateOf(false) }
    val lifecycleState by LocalLifecycleOwner.current.lifecycle.currentStateFlow.collectAsState()

    LaunchedEffect(lifecycleState) {
        checkPermission = lifecycleState == Lifecycle.State.RESUMED
    }

    if (checkPermission) {
        if (checkLocationPermission()) onPermissionsGranted()
        checkPermission = false
    }
}

@Composable
fun checkLocationPermission(): Boolean {
    return ContextCompat.checkSelfPermission(
        LocalContext.current, Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
}