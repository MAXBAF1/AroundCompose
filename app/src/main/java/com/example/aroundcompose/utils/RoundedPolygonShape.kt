package com.example.aroundcompose.utils

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Matrix
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.toPath
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.sin

class RoundedPolygonShape(
    private val polygon: RoundedPolygon,
    private var matrix: Matrix = Matrix()
) : Shape {
    private var path = Path()
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        path.rewind()
        path = polygon.toPath().asComposePath()
        matrix.reset()
        val bounds = polygon.getBounds()
        val maxDimension = max(bounds.width, bounds.height)
        matrix.scale(size.width / maxDimension, size.height / maxDimension)
        matrix.translate(bounds.right, bounds.right)
        matrix.rotateZ(30F)

        path.transform(matrix)
        return Outline.Generic(path)
    }
}

fun RoundedPolygon.getBounds() = calculateBounds().let { Rect(it[0], it[1], it[2], it[3]) }

fun createPolygonPath(cx: Float, cy: Float, sides: Int, radius: Float): Path {
    val angle = 2.0 * Math.PI / sides

    return Path().apply {
        moveTo(
            cx + (radius * cos(0.0)).toFloat(),
            cy + (radius * sin(0.0)).toFloat()
        )
        for (i in 1 until sides) {
            lineTo(
                cx + (radius * cos(angle * i)).toFloat(),
                cy + (radius * sin(angle * i)).toFloat()
            )
        }
        close()
    }
}