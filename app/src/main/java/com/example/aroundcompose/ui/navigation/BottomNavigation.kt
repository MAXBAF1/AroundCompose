package com.example.aroundcompose.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.aroundcompose.ui.theme.JetAroundTheme

@Composable
fun BottomNavigation(
    navController: NavController,
    listItems: List<Screens>,
    currentRoute: String,
) {
    NavigationBar(
        containerColor = JetAroundTheme.colors.primaryBackground
    ) {
        listItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.name,
                onClick = {
                    if (currentRoute == item.name) return@NavigationBarItem

                    else if (item.name == Screens.MapScreen.name) {
                        navController.navigate(item.name) {
                            popUpTo(item.name) { inclusive = true }
                        }
                    } else navController.navigate(item.name) { popUpTo(Screens.MapScreen.name) }
                },
                icon = {
                    if (item.iconId != null) {
                        Icon(
                            painter = painterResource(id = item.iconId),
                            contentDescription = "icon",
                            tint = if (currentRoute == item.name) {
                                JetAroundTheme.colors.textColor
                            } else JetAroundTheme.colors.lightTint
                        )
                    }
                },
                label = { /*Text(text = stringResource(id = item.titleId))*/ },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = JetAroundTheme.colors.textColor,
                    selectedTextColor = JetAroundTheme.colors.textColor,
                    unselectedIconColor = JetAroundTheme.colors.lightTint,
                    unselectedTextColor = JetAroundTheme.colors.lightTint,
                    indicatorColor = Color.Transparent
                ),
                alwaysShowLabel = false
            )
        }
    }
}