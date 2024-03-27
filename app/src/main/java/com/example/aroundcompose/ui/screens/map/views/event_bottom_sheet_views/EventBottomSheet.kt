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
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.example.aroundcompose.R
import com.example.aroundcompose.ui.common.views.SearchView
import com.example.aroundcompose.ui.theme.JetAroundTheme
import kotlinx.coroutines.delay

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
        ) {

        }
        TitleAndAll(
            titleText = stringResource(id = R.string.popular),
            onAllClick = { },
            modifier = Modifier.padding(bottom = 18.dp)
        )
        LazyRow(
            modifier = Modifier.padding(
                start = JetAroundTheme.margins.mainMargin,
                end = JetAroundTheme.margins.mainMargin,
                bottom = 24.dp
            )
        ) {

        }
        TitleAndAll(
            titleText = stringResource(id = R.string.closest),
            onAllClick = { },
            modifier = Modifier.padding(bottom = 18.dp)
        )
        LazyColumn {

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
            ), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = titleText,
            style = JetAroundTheme.typography.title,
            color = JetAroundTheme.colors.textColor
        )
        Text(
            modifier = Modifier.clickable(
                onClick = onAllClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = JetAroundTheme.colors.textColor)
            ),
            text = stringResource(id = R.string.all),
            style = JetAroundTheme.typography.medium,
            color = JetAroundTheme.colors.primary
        )
        val primary = JetAroundTheme.colors.primary
        var color by remember {
            mutableStateOf(primary)
        }
        ClickableText(
            text = AnnotatedString(stringResource(id = R.string.all)),
            style = JetAroundTheme.typography.medium.copy(color = color),
        ) {
            color = Color.DarkGray
            onAllClick()
        }

        if (color == Color.DarkGray) {
            LaunchedEffect(Unit) {
                delay(100)
                color = primary
            }
        }
    }
}