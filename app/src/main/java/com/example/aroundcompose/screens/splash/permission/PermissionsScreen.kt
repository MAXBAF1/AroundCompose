package com.example.aroundcompose.screens.splash.permission

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.aroundcompose.screens.splash.permission.models.PermissionsAction
import com.example.aroundcompose.screens.splash.permission.models.PermissionsEvent
import com.example.aroundcompose.screens.splash.permission.views.PermissionsBottomSheet

@Composable
fun PermissionsScreen(
    viewModel: PermissionsViewModel,
    onBackPressed: () -> Unit,
    onPermissionsGranted: () -> Unit,
) {
    val viewAction by viewModel.viewAction.collectAsState()

    when (viewAction) {
        PermissionsAction.CheckGranted -> {
            viewModel.obtainEvent(viewEvent = PermissionsEvent.CheckGranted)
        }

        PermissionsAction.NotGranted -> {
            viewModel.obtainEvent(viewEvent = PermissionsEvent.NotGranted)
        }

        PermissionsAction.PermissionNotAllowed -> {
            PermissionsBottomSheet(onBackPressed = onBackPressed, onOpenAppSettings = {
                viewModel.obtainEvent(viewEvent = PermissionsEvent.AppSettings)
            })
        }

        PermissionsAction.Granted -> onPermissionsGranted()
    }
}

