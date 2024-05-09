package com.example.aroundcompose.ui.common.views

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.aroundcompose.ui.theme.JetAroundTheme

@Composable
fun CustomIconButton(
    onClick: () -> Unit, iconId: Int, description: String, modifier: Modifier = Modifier
) {
    IconButton(modifier = modifier, onClick = onClick) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = description,
            tint = JetAroundTheme.colors.textColor
        )
    }
}
