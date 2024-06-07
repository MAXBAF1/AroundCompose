package com.example.aroundcompose.ui.screens.settings.views

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.aroundcompose.ui.screens.settings.models.ThemeTabs
import com.example.aroundcompose.ui.theme.JetAroundTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
class CustomTabRow(private val pagerState: PagerState, private val tabList: Array<ThemeTabs>) {

    @Composable
    fun Create() {
        val coroutineScope = rememberCoroutineScope()
        val tabIndex by remember { derivedStateOf { pagerState.currentPage } }
        val indicator = @Composable { tabPositions: List<TabPosition> ->
            CustomIndicator(tabPositions, pagerState)
        }
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(JetAroundTheme.colors.veryLightGray)
        ) {
            TabRow(modifier = Modifier.padding(4.dp),
                containerColor = JetAroundTheme.colors.veryLightGray,
                selectedTabIndex = tabIndex,
                indicator = indicator,
                divider = {}) {
                tabList.forEachIndexed { index, themeTab ->
                    CustomTab(index, themeTab = themeTab) {
                        coroutineScope.launch { pagerState.animateScrollToPage(index) }
                    }
                }
            }
        }
    }

    @Composable
    private fun CustomIndicator(tabPositions: List<TabPosition>, pagerState: PagerState) {
        val transition = updateTransition(pagerState.currentPage, label = "")
        val indicatorStart by transition.animateDp(
            transitionSpec = {
                if (initialState < targetState) {
                    spring(dampingRatio = 1f, stiffness = 50f)
                } else {
                    spring(dampingRatio = 1f, stiffness = 1000f)
                }
            }, label = ""
        ) { tabPositions[it].left }

        val indicatorEnd by transition.animateDp(
            transitionSpec = {
                if (initialState < targetState) {
                    spring(dampingRatio = 1f, stiffness = 1000f)
                } else {
                    spring(dampingRatio = 1f, stiffness = 50f)
                }
            }, label = ""
        ) { tabPositions[it].right }

        Box(
            Modifier
                .offset(x = indicatorStart)
                .wrapContentSize(align = Alignment.BottomStart)
                .width(indicatorEnd - indicatorStart)
                .fillMaxSize()
                .background(color = JetAroundTheme.colors.primaryBackground, CircleShape)
        )
    }

    @Composable
    private fun CustomTab(index: Int, themeTab: ThemeTabs, onClick: () -> Unit) {
        val selected = pagerState.currentPage == index
        val contentColor = if (selected) {
            JetAroundTheme.colors.textColor
        } else JetAroundTheme.colors.darkGray

        Box(
            modifier = Modifier
                .clip(CircleShape)
                .height(34.dp)
                .zIndex(1f)
                .clickable(
                    onClick = onClick,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ), contentAlignment = Alignment.Center
        ) {
            if (themeTab.text != null) {
                Text(
                    text = stringResource(id = themeTab.text),
                    style = JetAroundTheme.typography.medium16,
                    color = contentColor
                )
            } else if (themeTab.icon != null) {
                Icon(
                    painter = painterResource(id = themeTab.icon),
                    contentDescription = "icon",
                    tint = contentColor
                )
            }
        }
    }
}