package com.example.aroundcompose.ui.screens.registration.models.validation_data

import android.util.Log
import com.example.aroundcompose.R
import com.example.aroundcompose.ui.common.enums.FieldType

object ErrorMessages {
    private val loginErrorMessages = mapOf(
        ErrorsKeys.LENGTH to R.string.login_error_length,
        ErrorsKeys.REGEX to R.string.login_error_ABC_digit
    )
    private val emailErrorMessages = mapOf(ErrorsKeys.REGEX to R.string.email_error_domain)
    private val passwordErrorMessages = mapOf(
        ErrorsKeys.LENGTH  to R.string.password_error_length,
        ErrorsKeys.REGEX to R.string.password_error_ABC_digit_special
    )
    private val confirmPasswordErrorMessages = mapOf(
        ErrorsKeys.EQUALS to R.string.password_confirm_error_equals
    )

    fun getErrorMessages(type: FieldType, key: ErrorsKeys): Int? {
        return when (type) {
            FieldType.LOGIN -> loginErrorMessages[key]
            FieldType.EMAIL -> emailErrorMessages[key]
            FieldType.PASSWORD -> passwordErrorMessages[key]
            else -> confirmPasswordErrorMessages[key]
        }
    }
}