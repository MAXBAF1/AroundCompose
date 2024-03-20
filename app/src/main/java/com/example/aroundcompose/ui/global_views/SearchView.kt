package com.example.aroundcompose.ui.global_views

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.aroundcompose.R
import com.example.aroundcompose.ui.theme.JetAroundTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(modifier: Modifier, restoredValue: String, onValueChange: (String) -> Unit) {
    var value by remember { mutableStateOf(restoredValue) }
    LaunchedEffect(key1 = restoredValue) {
        value = restoredValue
    }
    val interactionSource = remember { MutableInteractionSource() }
    BasicTextField(
        value = value,
        onValueChange = {
            value = it
            onValueChange(it)
        },
        modifier = modifier,
        interactionSource = interactionSource,
        singleLine = true,
        textStyle = JetAroundTheme.typography.search
    ) { innerTextField ->
        TextFieldDefaults.DecorationBox(value = value,
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search_hint),
                    style = JetAroundTheme.typography.search,
                    color = JetAroundTheme.colors.searchHint
                )
            },
            visualTransformation = VisualTransformation.None,
            innerTextField = innerTextField,
            singleLine = true,
            enabled = true,
            interactionSource = interactionSource,
            contentPadding = PaddingValues(0.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = JetAroundTheme.colors.primaryBackground,
                focusedContainerColor = JetAroundTheme.colors.primaryBackground,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(12.dp),
            trailingIcon = {
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .padding(0.dp),
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "ic_search",
                    tint = JetAroundTheme.colors.mapSearchInactive
                )
            })
    }
}

/*
@Composable
private fun DecorationBox(
    hint: String,
    textValue: String,
    isFocused: Boolean,
    passwordVisible: MutableState<Boolean>?,
    leadingIcon: Painter,
    trailingIcon: Painter?,
    innerTextField: @Composable () -> Unit
) {
    Row(
        modifier = Modifier
            .background(
                color = colorBackground,
                shape = RoundedCornerShape(ComponentsShape.getShapeDp("large"))
            )
            .border(
                width = 1.dp,
                color = if (isFocused) {
                    colorLink
                } else if (textValue.isNotEmpty()) {
                    colorOnSecondary
                } else {
                    colorNotActive
                },
                shape = RoundedCornerShape(ComponentsShape.getShapeDp("large"))
            )
            .padding(vertical = 16.dp, horizontal = 16.dp) // Inner padding
    ) {
        Icon(
            painter = leadingIcon,
            contentDescription = "",
            tint = colorNotActive,
            modifier = Modifier
                .padding(end = 10.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            if (textValue.isEmpty()) {
                NormalTextComponent(hint, colorNotActive)
            }
            innerTextField()
        }

        if (trailingIcon == null) return

        val iconResourceId =
            if (passwordVisible?.value == true) R.drawable.ic_visible else R.drawable.ic_invisible

        androidx.compose.material3.IconButton(
            onClick = { passwordVisible?.value = !passwordVisible?.value!! },
            modifier = Modifier
                .height(24.dp)
                .width(26.dp)
                .wrapContentWidth(align = Alignment.End)
                .padding(start = 8.dp)
                .weight(0.18f)
                .align(Alignment.CenterVertically)
        ) {
            Icon(
                painter = painterResource(iconResourceId),
                contentDescription = "",
                tint = colorNotActive
            )
        }
    }
}
*/
