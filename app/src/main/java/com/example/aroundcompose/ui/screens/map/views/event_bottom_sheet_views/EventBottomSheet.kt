package com.example.aroundcompose.ui.screens.map.views.event_bottom_sheet_views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.aroundcompose.R
import com.example.aroundcompose.ui.common.models.EventData
import com.example.aroundcompose.ui.common.views.SearchView
import com.example.aroundcompose.ui.theme.JetAroundTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventBottomSheet(onDismissRequest: () -> Unit) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 22.dp),
        onDismissRequest = onDismissRequest,
        containerColor = JetAroundTheme.colors.primaryBackground,
        sheetState = sheetState
    ) {
        SearchView(
            restoredValue = "", modifier = Modifier.padding(
                start = JetAroundTheme.margins.mainMargin,
                end = JetAroundTheme.margins.mainMargin,
                bottom = 24.dp
            )
        ) { }
        TitleAndAll(
            titleText = stringResource(id = R.string.popular),
            onAllClick = { },
            modifier = Modifier.padding(bottom = 14.dp)
        )
        PopularEventsRow()
        TitleAndAll(
            titleText = stringResource(id = R.string.closest),
            onAllClick = { },
        )
        ClosestEventsColumn()
    }
}

@Composable
private fun PopularEventsRow() {
    LazyRow(
        modifier = Modifier.padding(bottom = 24.dp)
    ) {
        val exampleEventData =
            EventData("EKB STADIUM MEET 2023", "Екатеринбург Арена, Екатеринбург", 16.5)
        items(7) {
            val modifier = when (it) {
                0 -> Modifier.padding(start = JetAroundTheme.margins.mainMargin, end = 16.dp)
                6 -> Modifier.padding(end = 30.dp)
                else -> Modifier.padding(end = 16.dp)
            }
            PopularEventCard(eventData = exampleEventData, modifier = modifier)
        }
    }
}

@Composable
private fun ClosestEventsColumn() {
    LazyColumn(modifier = Modifier.padding(horizontal = JetAroundTheme.margins.mainMargin)) {
        val exampleEventData =
            EventData("Спектакль «Сын Луны»", "Академический театр драмы, Екатерибург", 3.3)
        items(7) {
            val modifier = when (it) {
                0 -> Modifier.padding(top = 14.dp, bottom = 16.dp)
                6 -> Modifier.padding(bottom = JetAroundTheme.margins.mainMargin)
                else -> Modifier.padding(bottom = 16.dp)
            }
            ClosestEventCard(eventData = exampleEventData, modifier = modifier)
        }
    }
}

@Composable
private fun TitleAndAll(titleText: String, onAllClick: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = JetAroundTheme.margins.mainMargin, end = JetAroundTheme.margins.mainMargin
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = titleText,
            style = JetAroundTheme.typography.title,
            color = JetAroundTheme.colors.textColor
        )
        Text(
            modifier = Modifier
                .clip(CircleShape)
                .clickable(
                    onClick = onAllClick,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(color = JetAroundTheme.colors.textColor)
                )
                .padding(6.dp),
            text = stringResource(id = R.string.all),
            style = JetAroundTheme.typography.medium,
            color = JetAroundTheme.colors.primary
        )
    }
}