package com.example.aroundcompose.ui.common.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.aroundcompose.R
import com.example.aroundcompose.ui.common.enums.FieldType
import com.example.aroundcompose.ui.theme.JetAroundTheme

@Composable
fun TextFieldView(
    textFieldType: FieldType,
    restoredValue: String,
    hint: String,
    leadingIcon: Painter,
    onValueChange: (value: String) -> Unit,
) {
    var textValue by remember { mutableStateOf(restoredValue) }
    var isFocused by remember { mutableStateOf(false) }
    val passwordVisible =
        if (textFieldType == FieldType.EMAIL || textFieldType == FieldType.LOGIN) {
            null
        } else remember { mutableStateOf(false) }

    val visualTransformation =
        if (passwordVisible == null) {
            VisualTransformation.None
        } else {
            if (passwordVisible.value) {
                VisualTransformation.None
            } else PasswordVisualTransformation()
        }

    Box {
        BasicTextField(value = textValue,
            onValueChange = {
                textValue = it
                onValueChange(it)
            },
            singleLine = true,
            modifier = Modifier
                .padding(top = 4.dp)
                .background(JetAroundTheme.colors.primaryBackground.copy(alpha = 0f))
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                },
            textStyle = JetAroundTheme.typography.textField,
            cursorBrush = SolidColor(JetAroundTheme.colors.textColor),
            keyboardOptions = KeyboardOptions(
                keyboardType = when (textFieldType) {
                    FieldType.LOGIN -> KeyboardType.Text
                    FieldType.EMAIL -> KeyboardType.Email
                    FieldType.PASSWORD -> KeyboardType.Password
                    FieldType.CONFIRM_PASSWORD -> KeyboardType.Password
                },
                imeAction = if (
                    textFieldType == FieldType.CONFIRM_PASSWORD || textFieldType == FieldType.EMAIL
                ) {
                    ImeAction.Done
                } else ImeAction.Next
            ),
            visualTransformation = visualTransformation,
            decorationBox = { innerTextField ->
                DecorationBox(
                    hint = hint,
                    textValue = textValue,
                    isFocused = isFocused,
                    passwordVisible = passwordVisible,
                    leadingIcon = leadingIcon,
                    innerTextField = innerTextField
                )
            })

        if (textValue.isNotEmpty()) {
            Text(
                text = hint,
                color = if (isFocused) {
                    JetAroundTheme.colors.onFocusedColor
                } else JetAroundTheme.colors.textColor,
                style = JetAroundTheme.typography.informationText,
                modifier = Modifier
                    .padding(start = 20.dp)
                    .background(JetAroundTheme.colors.primaryBackground)
                    .padding(horizontal = 4.dp)
            )
        }
    }
}

@Composable
private fun DecorationBox(
    hint: String,
    textValue: String,
    isFocused: Boolean,
    passwordVisible: MutableState<Boolean>?,
    leadingIcon: Painter,
    innerTextField: @Composable () -> Unit,
) {
    val tint = if (textValue.isEmpty()) {
        JetAroundTheme.colors.textFieldHint
    } else JetAroundTheme.colors.textColor

    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = Modifier
            .background(
                color = JetAroundTheme.colors.primaryBackground,
                shape = JetAroundTheme.shapes.textFieldShape
            )
            .border(
                width = if (isFocused) 3.dp else 2.dp,
                color = if (isFocused) {
                    JetAroundTheme.colors.onFocusedColor
                } else tint, shape = JetAroundTheme.shapes.textFieldShape
            )
            .padding(vertical = 12.dp, horizontal = 24.dp) // Inner padding
    ) {
        Icon(
            painter = leadingIcon,
            contentDescription = "",
            tint = tint,
            modifier = Modifier
                .padding(end = 16.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            if (textValue.isEmpty()) {
                Text(text = hint, color = JetAroundTheme.colors.textFieldHint)
            }
            innerTextField()
        }

        if (passwordVisible == null) return
        val trailingIconId =
            if (passwordVisible.value) R.drawable.ic_invisible else R.drawable.ic_visible

        Icon(
            painter = painterResource(trailingIconId),
            contentDescription = "",
            tint = tint,
            modifier = Modifier
                .wrapContentWidth(align = Alignment.End)
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
                .clickable {
                    passwordVisible.value = !passwordVisible.value
                }
        )
    }
}