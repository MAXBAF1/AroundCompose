package com.example.aroundcompose.ui.common.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
fun NavBackView(textId: Int, onBackClick: () -> Unit) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                painter = painterResource(id = textId),
                contentDescription = "icon",
                modifier = Modifier.clickable {
                    onBackClick()
                }
            )

            Text(
                text = stringResource(id = R.string.registration),
                style = JetAroundTheme.typography.heading,
                color = JetAroundTheme.colors.textColor,
                modifier = Modifier.padding(start = 24.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(JetAroundTheme.colors.textColor)
        )
    }
}