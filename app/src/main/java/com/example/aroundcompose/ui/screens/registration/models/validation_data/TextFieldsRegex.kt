package com.example.aroundcompose.ui.screens.registration.models.validation_data

import com.example.aroundcompose.ui.common.enums.FieldType

object TextFieldsRegex {
    private val loginRegex: Regex = Regex("[a-zA-Z0-9]+", setOf(RegexOption.IGNORE_CASE))
    private val emailRegex: Regex =
        Regex("""^[\w-.]+@([\w-]+\.)+[\w-]+""", setOf(RegexOption.IGNORE_CASE))
    private val passwordRegex: Regex =
        Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#%^&*()_+\\-=:;”’{}<>?/|`~,.])[a-zA-Z\\d!@#%^&*()_+\\-=:;”’{}<>?/|`~,.]{8,100}\$")

    fun getRegex(type: FieldType): Regex {
        return when (type) {
            FieldType.LOGIN -> return loginRegex
            FieldType.EMAIL -> return emailRegex
            FieldType.PASSWORD -> return passwordRegex
            else -> Regex("")
        }
    }
}