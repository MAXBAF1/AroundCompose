package com.example.aroundcompose.ui.common.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.aroundcompose.R
import com.example.aroundcompose.ui.theme.JetAroundTheme

@Composable
fun CustomTopAppBar(
    textId: Int,
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit)? = null,
    showMoney: Boolean = false,
    numberOfCoins: Int = 1335,
    trailingIconId: Int? = null,
    onTrailingBtnClick: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .padding(
                WindowInsets.systemBars
                    .only(WindowInsetsSides.Top)
                    .asPaddingValues()
            )
            .padding(
                top = if (showMoney) {
                    20.dp
                } else if (onBackClick != null || trailingIconId != null) {
                    18.dp
                } else JetAroundTheme.margins.mainMargin
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = if (showMoney) {
                    10.dp
                } else if (onBackClick != null || trailingIconId != null) {
                    8.dp
                } else 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (onBackClick != null) {
                    CustomIconButton(
                        onClick = onBackClick,
                        iconId = R.drawable.ic_arrow_left,
                        description = "back icon",
                        modifier = Modifier.padding(end = 16.dp)
                    )
                }
                Text(
                    text = stringResource(id = textId).uppercase(),
                    style = JetAroundTheme.typography.bold24,
                    color = JetAroundTheme.colors.textColor
                )
            }
            if (trailingIconId != null) {
                CustomIconButton(
                    onClick = onTrailingBtnClick,
                    iconId = trailingIconId,
                    description = "right icon"
                )
            }
            if (showMoney) {
                CoinView(
                    modifier,
                    value = numberOfCoins,
                    backgroundColor = JetAroundTheme.colors.primary,
                    contentColor = JetAroundTheme.colors.primaryBackground
                )
            }
        }
        HorizontalDivider(thickness = 4.dp, color = JetAroundTheme.colors.textColor)
    }
}