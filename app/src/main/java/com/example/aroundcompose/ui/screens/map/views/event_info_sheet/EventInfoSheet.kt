package com.example.aroundcompose.ui.screens.map.views.event_info_sheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.aroundcompose.ui.common.models.EventData
import com.example.aroundcompose.ui.theme.JetAroundTheme

class EventInfoSheet(private val eventData: EventData, private val onDismissRequest: () -> Unit) {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Create() {
        val sheetState = rememberModalBottomSheetState(confirmValueChange = {
            when (it) {
                SheetValue.Hidden -> false
                SheetValue.Expanded -> true
                SheetValue.PartiallyExpanded -> true
            }
        })

        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = onDismissRequest,
            scrimColor = Color.Transparent,
            containerColor = JetAroundTheme.colors.primaryBackground
        ) {
            Column(
                modifier = Modifier.padding(
                    start = JetAroundTheme.margins.mainMargin,
                    top = 0.dp,
                    end = JetAroundTheme.margins.mainMargin,
                    bottom = 24.dp
                )
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 16.dp),
                    text = eventData.title,
                    style = JetAroundTheme.typography.bold24,
                    color = JetAroundTheme.colors.textColor
                )
                Text(
                    text = eventData.description,
                    style = JetAroundTheme.typography.regular14,
                    color = JetAroundTheme.colors.textColor
                )
            }
        }
    }
}