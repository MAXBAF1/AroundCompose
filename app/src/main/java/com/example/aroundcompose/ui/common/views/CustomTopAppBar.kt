package com.example.aroundcompose.ui.common.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
fun CustomTopAppBar(
    textId: Int,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    isBackButtonNeeded: Boolean = true,
    showMoney: Boolean = false,
    trailingIconId: Int? = null,
    onTrailingBtnClick: () -> Unit = {},
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (isBackButtonNeeded) {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier.padding(end = 16.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_left),
                            contentDescription = "back icon"
                        )
                    }
                }
                Text(
                    text = stringResource(id = textId).uppercase(),
                    style = JetAroundTheme.typography.bigBold,
                    color = JetAroundTheme.colors.textColor
                )
            }
            if (trailingIconId != null) {
                IconButton(onClick = onTrailingBtnClick) {
                    Icon(
                        painter = painterResource(id = trailingIconId),
                        contentDescription = "right icon"
                    )
                }
            }
            if (showMoney) {
                CoinView(modifier,
                    value = 1365,
                    backgroundColor = JetAroundTheme.colors.primary,
                    contentColor = JetAroundTheme.colors.primaryBackground
                )
            }
        }
        HorizontalDivider(thickness = 4.dp, color = JetAroundTheme.colors.textColor)
    }
}