package com.example.aroundcompose.screens.splash.permission

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import com.example.aroundcompose.screens.splash.permission.models.PermissionsAction
import com.example.aroundcompose.screens.splash.permission.models.PermissionsEvent
import com.example.aroundcompose.screens.splash.permission.views.PermissionsBottomSheet

@Composable
fun PermissionsScreen(
    viewModel: PermissionsViewModel,
    onBackPressed: () -> Unit,
    onPermissionsGranted: () -> Unit,
) {
    val viewAction by viewModel.getViewAction().collectAsState()

    when (viewAction) {
        PermissionsAction.CheckGranted -> {
            viewModel.composableObtainEvent(viewEvent = PermissionsEvent.CheckGranted)
        }

        PermissionsAction.NotGranted -> {
            viewModel.composableObtainEvent(viewEvent = PermissionsEvent.NotGranted)
        }

        PermissionsAction.PermissionNotAllowed -> {
            PermissionsBottomSheet(onBackPressed = onBackPressed, onOpenAppSettings = {
                viewModel.composableObtainEvent(viewEvent = PermissionsEvent.AppSettings)
            })
        }

        PermissionsAction.Granted -> onPermissionsGranted()
    }
}

