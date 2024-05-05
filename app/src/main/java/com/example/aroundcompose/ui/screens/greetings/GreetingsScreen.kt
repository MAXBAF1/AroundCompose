package com.example.aroundcompose.ui.screens.greetings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import com.example.aroundcompose.R
import com.example.aroundcompose.ui.common.enums.Teams
import com.example.aroundcompose.ui.theme.JetAroundTheme
import com.example.aroundcompose.utils.RoundedPolygonShape
import kotlinx.coroutines.delay

@Composable
fun GreetingsScreen(team: Teams, navigateToNextScreen: () -> Unit) {
    var progress by remember { mutableFloatStateOf(0f) }
    val shape = RoundedPolygonShape(
        RoundedPolygon(numVertices = 6, rounding = CornerRounding(radius = 0.14F))
    )
    val backgroundColor = when (team) {
        Teams.NONE -> JetAroundTheme.colors.blue
        Teams.LIGHT_BLUE -> JetAroundTheme.colors.lightBlue
        Teams.YELLOW -> JetAroundTheme.colors.yellow
        Teams.PURPLE -> JetAroundTheme.colors.purple
        Teams.BLUE -> JetAroundTheme.colors.blue
    }

    LaunchedEffect(Unit) { // Переделать
        repeat(100) {
            delay(30)
            progress += 0.0175f
        }
        navigateToNextScreen()
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(JetAroundTheme.colors.primaryBackground)
    ) {
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .padding(end = 40.dp)
                .rotate(-11.5f)
                .clip(shape)
                .size(162.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(162.dp)
                    .height((progress * 100).dp)
                    .background(backgroundColor)
                    .align(Alignment.BottomCenter)

            )
        }
        Image(painter = painterResource(id = R.drawable.mascot), contentDescription = "mascot")
    }
}



