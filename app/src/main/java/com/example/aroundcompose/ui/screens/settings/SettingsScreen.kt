package com.example.aroundcompose.ui.screens.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.aroundcompose.R
import com.example.aroundcompose.ui.common.views.CustomButton
import com.example.aroundcompose.ui.common.views.CustomTopAppBar
import com.example.aroundcompose.ui.screens.settings.models.ThemeTabs
import com.example.aroundcompose.ui.screens.settings.views.CustomSwitch
import com.example.aroundcompose.ui.screens.settings.views.CustomTabRow
import com.example.aroundcompose.ui.theme.JetAroundTheme

class SettingsScreen(private val onBackClick: () -> Unit) {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun Create() {
        Scaffold {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = JetAroundTheme.margins.mainMargin, bottom = 16.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                CustomTopAppBar(
                    modifier = Modifier.padding(
                        start = JetAroundTheme.margins.mainMargin,
                        end = JetAroundTheme.margins.mainMargin,
                        bottom = 24.dp
                    ), textId = R.string.settings, onBackClick = onBackClick
                )
                NotificationsRow(
                    modifier = Modifier.padding(
                        start = JetAroundTheme.margins.mainMargin,
                        end = JetAroundTheme.margins.mainMargin,
                        bottom = 14.dp
                    )
                )
                RowToDetailsScreen(
                    modifier = Modifier.padding(bottom = 18.dp),
                    onClick = {},
                    title = stringResource(id = R.string.language),
                    hint = stringResource(id = R.string.russian),
                )
                ThemePicker(
                    modifier = Modifier.padding(
                        start = JetAroundTheme.margins.mainMargin,
                        end = JetAroundTheme.margins.mainMargin,
                        bottom = 8.dp
                    )
                )
                RowToDetailsScreen(
                    onClick = {}, title = stringResource(id = R.string.name), hint = "@MAXBAF1"
                )
                RowToDetailsScreen(
                    onClick = {},
                    title = stringResource(id = R.string.mail),
                    hint = "marina.volkova@gmail.com"
                )
                RowToDetailsScreen(
                    onClick = {}, title = stringResource(id = R.string.password)
                )
                Spacer(modifier = Modifier.weight(1f))
                ExitBtn(onClick = { }, modifier = Modifier.padding(bottom = 16.dp))
                CustomButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(
                        start = JetAroundTheme.margins.mainMargin,
                        end = JetAroundTheme.margins.mainMargin,
                        bottom = 16.dp
                    ),
                    text = stringResource(id = R.string.about_app).uppercase(),
                )
                CustomButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(
                        start = JetAroundTheme.margins.mainMargin,
                        end = JetAroundTheme.margins.mainMargin
                    ),
                    text = stringResource(id = R.string.delete_account).uppercase(),
                    containerColor = JetAroundTheme.colors.notActiveColor
                )
            }
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun ThemePicker(modifier: Modifier = Modifier) {
        val tabList = ThemeTabs.values()
        val pagerState = rememberPagerState { tabList.size }

        Column(
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.padding(bottom = 14.dp),
                text = stringResource(id = R.string.theme),
                style = JetAroundTheme.typography.sixteenMedium,
                color = JetAroundTheme.colors.textColor
            )
            CustomTabRow(pagerState, tabList).Create()
            HorizontalPager(state = pagerState) { }
        }
    }

    @Composable
    private fun RowToDetailsScreen(
        onClick: () -> Unit, title: String, modifier: Modifier = Modifier, hint: String = ""
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clickable(
                    onClick = onClick,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple()
                )
                .padding(horizontal = JetAroundTheme.margins.mainMargin, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = JetAroundTheme.typography.sixteenMedium,
                color = JetAroundTheme.colors.textColor
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    modifier = Modifier.padding(end = 4.dp),
                    text = hint,
                    style = JetAroundTheme.typography.fourteenMedium,
                    color = JetAroundTheme.colors.lightTint
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_go_to),
                    contentDescription = "toDetailsScreenIcon",
                    tint = JetAroundTheme.colors.lightTint
                )
            }
        }
    }

    @Composable
    private fun NotificationsRow(modifier: Modifier = Modifier) {
        var checked by remember { mutableStateOf(true) }

        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.notifications),
                style = JetAroundTheme.typography.sixteenMedium,
                color = JetAroundTheme.colors.textColor
            )
            CustomSwitch(
                checked = checked,
                onCheckedChange = { checked = !checked },
                thumbColor = JetAroundTheme.colors.primaryBackground,
                checkedTrackColor = JetAroundTheme.colors.primary,
                uncheckedTrackColor = JetAroundTheme.colors.gray
            )
        }
    }

    @Composable
    private fun ExitBtn(onClick: () -> Unit, modifier: Modifier = Modifier) {
        Button(
            modifier = modifier,
            onClick = onClick,
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = JetAroundTheme.colors.gray),
            elevation = ButtonDefaults.buttonElevation(4.dp),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.exit_from_profile),
                    color = JetAroundTheme.colors.textColor,
                    style = JetAroundTheme.typography.sixteenMedium,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_exit),
                    contentDescription = "exit icon",
                    tint = JetAroundTheme.colors.textColor
                )
            }
        }
    }
}