package com.example.aroundcompose.ui.screens.greetings

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.aroundcompose.R
import com.example.aroundcompose.ui.common.enums.Teams
import com.example.aroundcompose.ui.navigation.Screens
import com.example.aroundcompose.ui.theme.JetAroundTheme
import com.example.aroundcompose.utils.RoundedPolygonShape
import com.example.aroundcompose.utils.UpdateThemeStyleByTeam

@Composable
fun GreetingsScreen(toOtherScreen: (Screens) -> Unit) {
    val viewModel = hiltViewModel<GreetingsViewModel>()
    val viewState by viewModel
        .getViewState()
        .collectAsStateWithLifecycle()

    UpdateThemeStyleByTeam(viewState.team)

    var isAnimationStart by remember { mutableStateOf(false) }
    var isAnimationEnd by remember { mutableStateOf(false) }

    val height by animateDpAsState(
        targetValue = if (isAnimationStart) 162.dp else 0.dp,
        animationSpec = tween(durationMillis = 3000),
        label = "download animation"
    ) { isAnimationEnd = true }

    LaunchedEffect(key1 = Unit) { isAnimationStart = true }

    if (isAnimationEnd && viewState.newScreens != null) toOtherScreen(viewState.newScreens!!)

    val shape = RoundedPolygonShape(RoundedPolygon(6, rounding = CornerRounding(0.14F)))
    val backgroundColor = when (viewState.team) {
        Teams.BLUE -> JetAroundTheme.colors.blue
        Teams.LIGHT_BLUE -> JetAroundTheme.colors.lightBlue
        Teams.YELLOW -> JetAroundTheme.colors.yellow
        Teams.PURPLE -> JetAroundTheme.colors.purple
        Teams.NONE -> JetAroundTheme.colors.notActiveColor
    }

    Surface(color = JetAroundTheme.colors.primaryBackground) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize(),
        ) {
            Box(
                contentAlignment = Alignment.BottomCenter,
                modifier = Modifier
                    .padding(end = 40.dp)
                    .rotate(-11.5f)
                    .clip(shape)
                    .size(161.dp)
            ) {
                Box(
                    modifier = Modifier
                        .width(161.dp)
                        .height(height)
                        .background(backgroundColor)
                        .align(Alignment.BottomCenter)
                )
            }
            Image(painter = painterResource(id = R.drawable.mascot), contentDescription = "mascot")
        }
    }
}



