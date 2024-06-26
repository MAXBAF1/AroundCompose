package com.example.aroundcompose.ui.common.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.aroundcompose.R
import com.example.aroundcompose.ui.theme.JetAroundTheme

@Composable
fun CoinView(
    modifier: Modifier,
    value: Int,
    backgroundColor: androidx.compose.ui.graphics.Color = JetAroundTheme.colors.primaryBackground,
    contentColor: androidx.compose.ui.graphics.Color = JetAroundTheme.colors.darkGray
) {
    Card(
        modifier = modifier,
        shape = JetAroundTheme.shapes.mapElementsShape,
        elevation = CardDefaults.cardElevation(JetAroundTheme.shadows.mapElementsShadow)
    ) {
        Row(
            modifier = Modifier
                .background(backgroundColor)
                .padding(10.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(end = 5.dp),
                text = value.toString(),
                style = JetAroundTheme.typography.coin,
                color = contentColor,
                textAlign = TextAlign.Center,
            )
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.ic_coin),
                contentDescription = "ic_coin",
                tint = contentColor
            )
        }
    }
}