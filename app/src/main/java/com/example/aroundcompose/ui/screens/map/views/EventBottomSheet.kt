package com.example.aroundcompose.ui.screens.map.views

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.aroundcompose.ui.common.views.SearchView
import com.example.aroundcompose.ui.theme.JetAroundTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventBottomSheet(onDismissRequest: () -> Unit) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        modifier = Modifier.fillMaxSize().padding(top = 22.dp),
        onDismissRequest = onDismissRequest,
        containerColor = JetAroundTheme.colors.primaryBackground,
        sheetState = sheetState
    ) {
        SearchView(restoredValue = "", modifier = Modifier.padding(start = 30.dp, end = 30.dp)) {

        }
    }
}