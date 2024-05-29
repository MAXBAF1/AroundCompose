package com.example.aroundcompose.ui.screens.event_info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.aroundcompose.ui.common.models.EventData
import com.example.aroundcompose.ui.theme.JetAroundTheme

class EventInfoScreen(private val eventData: EventData) {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Create() {
        ModalBottomSheet(onDismissRequest = { /*TODO*/ }) {
            Column(modifier = Modifier.padding(horizontal = 30.dp, vertical = 24.dp)) {
                Text(
                    modifier = Modifier.padding(bottom = 14.dp),
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