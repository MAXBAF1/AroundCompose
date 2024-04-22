package com.example.aroundcompose.ui.screens.registration.models.validation_data

import android.util.Log
import com.example.aroundcompose.R
import com.example.aroundcompose.ui.common.enums.FieldType

object ErrorMessages {
    private val loginErrorMessages = mapOf(
        "Length" to R.string.login_error_length,
        "Regex" to R.string.login_error_ABC_digit
    )
    private val emailErrorMessages = mapOf("Regex" to R.string.email_error_domain)
    private val passwordErrorMessages = mapOf(
        "Length" to R.string.password_error_length,
        "Regex" to R.string.password_error_ABC_digit_special
    )
    private val confirmPasswordErrorMessages = mapOf(
        "Equals" to R.string.password_confirm_error_equals
    )

    fun getErrorMessages(type: FieldType, key: String): Int? {
        return when (type) {
            FieldType.LOGIN -> loginErrorMessages[key]
            FieldType.EMAIL -> emailErrorMessages[key]
            FieldType.PASSWORD -> passwordErrorMessages[key]
            else -> confirmPasswordErrorMessages[key]
        }
    }
}