package com.example.aroundcompose.ui.common.models

import com.example.aroundcompose.ui.common.enums.FieldType
import java.io.Serializable

interface IFields : Serializable {
    fun toList() : List<FieldData>

    operator fun set(type: FieldType, value: FieldData)
    operator fun get(type: FieldType): FieldData
}