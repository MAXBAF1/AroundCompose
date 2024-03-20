package com.example.aroundcompose.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController

@Composable
fun BottomNavigation(navController: NavController, currentRoute: String) {
    val listItems = Screen.getBottomItems()

    NavigationBar {
        listItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute == item.route) return@NavigationBarItem

                    if (item.route == Screen.MAP_ROUTE) {
                        navController.navigate(item.route) {
                            popUpTo(item.route) { inclusive = true }
                        }
                    } else navController.navigate(item.route) {
                        popUpTo(Screen.MAP_ROUTE)
                    }
                },
                icon = {
                    Icon(painter = painterResource(id = item.iconId), contentDescription = "icon")
                },
                label = { Text(text = stringResource(id = item.titleId)) },
            )
        }
    }
}