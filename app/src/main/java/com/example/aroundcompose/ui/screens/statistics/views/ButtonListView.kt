package com.example.aroundcompose.ui.screens.statistics.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.aroundcompose.ui.theme.JetAroundTheme

@Composable
fun ButtonListView(textId: Int, currentButton: Boolean = true, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(horizontal = 28.dp, vertical = 16.dp),
        colors = ButtonColors(
            contentColor = if (currentButton) {
                JetAroundTheme.colors.primary
            } else JetAroundTheme.colors.primaryBackground,
            containerColor = if (currentButton) {
                JetAroundTheme.colors.primaryBackground
            } else JetAroundTheme.colors.primary,
            disabledContentColor = JetAroundTheme.colors.primaryBackground,
            disabledContainerColor = JetAroundTheme.colors.primary
        ),
        border = if (currentButton) null else BorderStroke(
            2.dp,
            JetAroundTheme.colors.primaryBackground
        ),
        shape = JetAroundTheme.shapes.buttonListShape
    ) {
        Text(
            text = stringResource(id = textId).replaceFirstChar { it.uppercaseChar() },
            style = JetAroundTheme.typography.percent
        )
    }
}