package com.example.aroundcompose.ui.screens.authorization.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.aroundcompose.ui.theme.JetAroundTheme

@Composable
fun LoginUsingBtn(
    painterId: Int,
    stringId: Int,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        enabled = true,
        shape = JetAroundTheme.shapes.textFieldShape,
        border = BorderStroke(width = 2.dp, color = JetAroundTheme.colors.textFieldHint),
        elevation = ButtonDefaults.buttonElevation(JetAroundTheme.shadows.loginUsingShadow),
        contentPadding = PaddingValues(vertical = 13.dp),
        colors = ButtonColors(
            containerColor = JetAroundTheme.colors.primaryBackground,
            contentColor = JetAroundTheme.colors.textFieldHint,
            disabledContainerColor = JetAroundTheme.colors.primaryBackground,
            disabledContentColor = JetAroundTheme.colors.textFieldHint
        ),
        modifier = Modifier.width(157.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(painter = painterResource(id = painterId), contentDescription = "icon")
            Text(
                text = stringResource(id = stringId),
                style = JetAroundTheme.typography.textBtn,
                color = JetAroundTheme.colors.textFieldHint,
                modifier = Modifier
                    .padding(start = 8.dp)
            )
        }
    }
}