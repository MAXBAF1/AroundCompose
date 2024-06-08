package com.example.aroundcompose.ui.common.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.aroundcompose.ui.theme.JetAroundTheme

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    containerColor: Color = JetAroundTheme.colors.primary
) {
    TextButton(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        enabled = enabled,
        shape = JetAroundTheme.shapes.textFieldShape,
        elevation = ButtonDefaults.buttonElevation(JetAroundTheme.shadows.loginUsingShadow),
        colors = ButtonColors(
            containerColor = containerColor,
            contentColor = JetAroundTheme.colors.primaryBackground,
            disabledContainerColor = JetAroundTheme.colors.notActiveColor,
            disabledContentColor = JetAroundTheme.colors.primaryBackground
        ),
        contentPadding = PaddingValues(vertical = 21.dp)
    ) {
        Text(
            text = text, style = JetAroundTheme.typography.textBtn
        )
    }
}