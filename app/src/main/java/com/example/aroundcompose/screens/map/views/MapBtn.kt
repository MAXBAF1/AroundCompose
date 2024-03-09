package com.example.aroundcompose.screens.map.views

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.aroundcompose.ui.theme.JetAroundTheme

@Composable
fun MapBtn(iconId: Int, onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        modifier = Modifier.size(47.dp),
        shape = RoundedCornerShape(12.dp),
        containerColor = JetAroundTheme.colors.tintColor,
        elevation = FloatingActionButtonDefaults.elevation(0.dp)
    ) {
        Icon(painter = painterResource(id = iconId), contentDescription = "icon")
    }


}
