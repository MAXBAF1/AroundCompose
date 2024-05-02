package com.example.aroundcompose.ui.common.models

import com.example.aroundcompose.ui.common.enums.FieldType

interface IFields {
    fun toList() : List<FieldData>

    operator fun set(type: FieldType, value: FieldData)
    operator fun get(type: FieldType): FieldData
}