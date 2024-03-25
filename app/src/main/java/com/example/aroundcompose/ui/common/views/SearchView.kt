package com.example.aroundcompose.ui.common.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.aroundcompose.R
import com.example.aroundcompose.ui.theme.JetAroundTheme
import com.example.aroundcompose.utils.clearFocusOnKeyboardDismiss

@Composable
fun SearchView(modifier: Modifier, restoredValue: String, onValueChange: (String) -> Unit) {
    var value by remember { mutableStateOf(restoredValue) }
    LaunchedEffect(key1 = restoredValue) { value = restoredValue }

    Card(
        modifier = modifier,
        shape = JetAroundTheme.shapes.mapElementsShape,
        elevation = CardDefaults.cardElevation(JetAroundTheme.shadows.mapElementsShadow)
    ) {
        BasicTextField(
            modifier = Modifier.clearFocusOnKeyboardDismiss(),
            value = value,
            onValueChange = {
                value = it
                onValueChange(it)
            },
            singleLine = true,
            textStyle = JetAroundTheme.typography.search.copy(color = JetAroundTheme.colors.textColor),
        ) { innerTextField ->
            DecorationBox(
                textValue = value,
                trailingIconId = R.drawable.ic_search,
                innerTextField = innerTextField
            )
        }
    }
}


@Composable
private fun DecorationBox(
    textValue: String,
    trailingIconId: Int?,
    innerTextField: @Composable () -> Unit,
) {
    Row(
        modifier = Modifier
            .background(color = JetAroundTheme.colors.primaryBackground)
            .padding(vertical = 10.dp, horizontal = 15.dp), // Inner padding
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            if (textValue.isEmpty()) {
                Text(
                    text = stringResource(id = R.string.search_hint),
                    style = JetAroundTheme.typography.search,
                    color = JetAroundTheme.colors.searchHint
                )
            }
            innerTextField()
        }

        if (trailingIconId == null) return
        val tint = if (textValue.isEmpty()) {
            JetAroundTheme.colors.searchHint
        } else JetAroundTheme.colors.textColor
        Icon(
            painter = painterResource(trailingIconId), contentDescription = "icon", tint = tint
        )
    }
}
