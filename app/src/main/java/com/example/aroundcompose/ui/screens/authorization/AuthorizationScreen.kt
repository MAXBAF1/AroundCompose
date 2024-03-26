package com.example.aroundcompose.ui.screens.authorization

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.aroundcompose.R
import com.example.aroundcompose.ui.common.enums.FieldType
import com.example.aroundcompose.ui.common.views.TextFieldView
import com.example.aroundcompose.ui.theme.JetAroundTheme

class AuthorizationScreen(
    private val viewModel: AuthorizationViewModel,
    private val onLoginClicked: () -> Unit,
) {
    @Composable
    fun Create() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .paint(painterResource(id = R.drawable.background), contentScale = ContentScale.Fit)
                .padding(horizontal = 30.dp)
        ) {
            Spacer(modifier = Modifier.height(200.dp))

            Text(
                text = "добрый день!",
                style = JetAroundTheme.typography.headingLogin,
                color = JetAroundTheme.colors.onFocusedColor,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "введите свою почту и пароль, чтобы войти",
                style = JetAroundTheme.typography.textField,
                color = JetAroundTheme.colors.textColor,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(40.dp))

            TextFieldView(
                textFieldType = FieldType.EMAIL,
                restoredValue = "",
                hint = "Электронная почта",
                leadingIcon = painterResource(id = R.drawable.ic_email),
                onValueChange = {}
            )

            Spacer(modifier = Modifier.height(14.dp))

            TextFieldView(
                textFieldType = FieldType.PASSWORD,
                restoredValue = "",
                hint = "Пароль",
                leadingIcon = painterResource(id = R.drawable.ic_lock),
                onValueChange = {}
            )
        }
    }
}