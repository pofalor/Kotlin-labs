package com.example.kazan.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocalCafe
import androidx.compose.material.icons.outlined.LocalDining
import androidx.compose.material.icons.outlined.LocalFlorist
import androidx.compose.material.icons.outlined.StoreMallDirectory
import androidx.compose.material.icons.outlined.Toys
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kazan.R
import com.example.kazan.data.InMemoryRecommendationsRepository
import com.example.kazan.domain.Category
import com.example.kazan.ui.screens.CategoryRecommendationsScreen

private const val ROUTE_COFFEES = "coffees"
private const val ROUTE_RESTAURANTS = "restaurants"
private const val ROUTE_KIDS = "kids"
private const val ROUTE_PARKS = "parks"
private const val ROUTE_MALLS = "malls"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KazanApp(
    windowSizeClass: WindowWidthSizeClass,
) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: ROUTE_COFFEES
    val currentCategory = when (currentRoute) {
        ROUTE_COFFEES -> Category.COFFEES
        ROUTE_RESTAURANTS -> Category.RESTAURANTS
        ROUTE_KIDS -> Category.KIDS
        ROUTE_PARKS -> Category.PARKS
        ROUTE_MALLS -> Category.MALLS
        else -> Category.COFFEES
    }

    val repository = remember { InMemoryRecommendationsRepository() }

    val title = when (currentCategory) {
        Category.COFFEES -> stringResource(R.string.category_cafes)
        Category.RESTAURANTS -> stringResource(R.string.category_restaurants)
        Category.KIDS -> stringResource(R.string.category_kids)
        Category.PARKS -> stringResource(R.string.category_parks)
        Category.MALLS -> stringResource(R.string.category_malls)
    }

    val navItems = listOf(
        NavItem(
            route = ROUTE_COFFEES,
            labelRes = R.string.category_cafes,
            icon = Icons.Outlined.LocalCafe,
            category = Category.COFFEES,
        ),
        NavItem(
            route = ROUTE_RESTAURANTS,
            labelRes = R.string.category_restaurants,
            icon = Icons.Outlined.LocalDining,
            category = Category.RESTAURANTS,
        ),
        NavItem(
            route = ROUTE_KIDS,
            labelRes = R.string.category_kids,
            icon = Icons.Outlined.Toys,
            category = Category.KIDS,
        ),
        NavItem(
            route = ROUTE_PARKS,
            labelRes = R.string.category_parks,
            icon = Icons.Outlined.LocalFlorist,
            category = Category.PARKS,
        ),
        NavItem(
            route = ROUTE_MALLS,
            labelRes = R.string.category_malls,
            icon = Icons.Outlined.StoreMallDirectory,
            category = Category.MALLS,
        ),
    )

    if (windowSizeClass == WindowWidthSizeClass.Expanded) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Scaffold(
                topBar = {
                    TopAppBar(title = { Text(title) })
                },
            ) { contentPadding ->
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(contentPadding),
                    horizontalArrangement = Arrangement.Start,
                ) {
                    NavigationRail {
                        navItems.forEach { item ->
                            NavigationRailItem(
                                selected = currentRoute == item.route,
                                onClick = {
                                    navController.navigate(item.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                                icon = { androidx.compose.material3.Icon(item.icon, contentDescription = null) },
                                label = { Text(stringResource(item.labelRes)) },
                            )
                        }
                    }

                    Box(modifier = Modifier.weight(1f)) {
                        NavHost(
                            navController = navController,
                            startDestination = ROUTE_COFFEES,
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            composable(route = ROUTE_COFFEES) {
                                CategoryRecommendationsScreen(
                                    category = Category.COFFEES,
                                    repository = repository,
                                    windowSizeClass = windowSizeClass,
                                )
                            }
                            composable(route = ROUTE_RESTAURANTS) {
                                CategoryRecommendationsScreen(
                                    category = Category.RESTAURANTS,
                                    repository = repository,
                                    windowSizeClass = windowSizeClass,
                                )
                            }
                            composable(route = ROUTE_KIDS) {
                                CategoryRecommendationsScreen(
                                    category = Category.KIDS,
                                    repository = repository,
                                    windowSizeClass = windowSizeClass,
                                )
                            }
                            composable(route = ROUTE_PARKS) {
                                CategoryRecommendationsScreen(
                                    category = Category.PARKS,
                                    repository = repository,
                                    windowSizeClass = windowSizeClass,
                                )
                            }
                            composable(route = ROUTE_MALLS) {
                                CategoryRecommendationsScreen(
                                    category = Category.MALLS,
                                    repository = repository,
                                    windowSizeClass = windowSizeClass,
                                )
                            }
                        }
                    }
                }
            }
        }
    } else {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(title) })
            },
            bottomBar = {
                NavigationBar {
                    navItems.forEach { item ->
                        NavigationBarItem(
                            selected = currentRoute == item.route,
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                androidx.compose.material3.Icon(item.icon, contentDescription = null)
                            },
                            label = { Text(stringResource(item.labelRes)) },
                            alwaysShowLabel = true,
                        )
                    }
                }
            },
        ) { contentPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding),
            ) {
                NavHost(
                    navController = navController,
                    startDestination = ROUTE_COFFEES,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    composable(route = ROUTE_COFFEES) {
                        CategoryRecommendationsScreen(
                            category = Category.COFFEES,
                            repository = repository,
                            windowSizeClass = windowSizeClass,
                        )
                    }
                    composable(route = ROUTE_RESTAURANTS) {
                        CategoryRecommendationsScreen(
                            category = Category.RESTAURANTS,
                            repository = repository,
                            windowSizeClass = windowSizeClass,
                        )
                    }
                    composable(route = ROUTE_KIDS) {
                        CategoryRecommendationsScreen(
                            category = Category.KIDS,
                            repository = repository,
                            windowSizeClass = windowSizeClass,
                        )
                    }
                    composable(route = ROUTE_PARKS) {
                        CategoryRecommendationsScreen(
                            category = Category.PARKS,
                            repository = repository,
                            windowSizeClass = windowSizeClass,
                        )
                    }
                    composable(route = ROUTE_MALLS) {
                        CategoryRecommendationsScreen(
                            category = Category.MALLS,
                            repository = repository,
                            windowSizeClass = windowSizeClass,
                        )
                    }
                }
            }
        }
    }
}

private data class NavItem(
    val route: String,
    val labelRes: Int,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val category: Category,
)

