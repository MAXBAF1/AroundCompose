package com.example.aroundcompose.ui.common.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.aroundcompose.R
import com.example.aroundcompose.ui.theme.JetAroundTheme

@Composable
fun NavBackView(textId: Int, onBackClick: () -> Unit) {
    Column {
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_left),
                contentDescription = "icon",
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(100))
                    .clickable(
                        onClick = onBackClick,
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(color = JetAroundTheme.colors.textColor)
                    )
                    .padding(8.dp)
            )

            Text(
                text = stringResource(id = textId).uppercase(),
                style = JetAroundTheme.typography.heading,
                color = JetAroundTheme.colors.textColor,
                modifier = Modifier.padding(start = 16.dp, bottom = 6.dp)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(JetAroundTheme.colors.textColor)
        )
    }
}