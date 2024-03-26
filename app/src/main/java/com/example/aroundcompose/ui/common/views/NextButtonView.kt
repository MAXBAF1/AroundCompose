package com.example.aroundcompose.ui.common.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.aroundcompose.R
import com.example.aroundcompose.ui.theme.JetAroundTheme

@Composable
fun NextButtonView(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        enabled = true,
        contentPadding = PaddingValues(vertical = 5.dp, horizontal = 16.dp),
        shape = JetAroundTheme.shapes.textFieldShape,
        colors = ButtonColors(
            containerColor = JetAroundTheme.colors.onFocusedColor,
            contentColor = JetAroundTheme.colors.primaryBackground,
            disabledContainerColor = JetAroundTheme.colors.onFocusedColor,
            disabledContentColor = JetAroundTheme.colors.primaryBackground
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.next_btn),
                style = JetAroundTheme.typography.textBtn,
                color = JetAroundTheme.colors.primaryBackground
            )

            Spacer(modifier = Modifier.width(8.dp))

            Icon(painter = painterResource(id = R.drawable.ic_arrow_right), contentDescription = "")
        }
    }
}