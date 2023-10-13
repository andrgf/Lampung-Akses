package com.example.newsapp.presentation.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.R
import com.example.newsapp.data.remote.dto.Article
import com.example.newsapp.presentation.navigation.component.NewsBottomBar
import com.example.newsapp.presentation.screen.bookmarks.BookmarksScreen
import com.example.newsapp.presentation.screen.detail.DetailScreen
import com.example.newsapp.presentation.screen.home.HomeScreen
import com.example.newsapp.presentation.screen.home.HomeViewModel
import com.example.newsapp.presentation.screen.profile.ProfileScreen
import com.example.newsapp.presentation.screen.search.SearchScreen
import com.example.newsapp.presentation.screen.search.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsNavigator() {

    val bottomNavigationItems = remember {
        listOf(
            NavigationItem(icon = R.drawable.home, title = "Home"),
            NavigationItem(icon = R.drawable.search, title = "Search"),
            NavigationItem(icon = R.drawable.bookmarks, title = "Bookmark"),
            NavigationItem(icon = R.drawable.profile, title = "Profile")
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }
    selectedItem = when (backStackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.SearchScreen.route -> 1
        Route.BookmarksScreen.route -> 2
        Route.ProfileScreen.route -> 3
        else -> 0
    }

    val bottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeScreen.route ||
                backStackState?.destination?.route == Route.SearchScreen.route ||
                backStackState?.destination?.route == Route.BookmarksScreen.route ||
                backStackState?.destination?.route == Route.ProfileScreen.route
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            if (bottomBarVisible) {
                NewsBottomBar(
                    items = bottomNavigationItems,
                    selectedItem = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navigateTo(
                                navController = navController,
                                route = Route.HomeScreen.route
                            )

                            1 -> navigateTo(
                                navController = navController,
                                route = Route.SearchScreen.route
                            )

                            2 -> navigateTo(
                                navController = navController,
                                route = Route.BookmarksScreen.route
                            )

                            3 -> navigateTo(
                                navController = navController,
                                route = Route.ProfileScreen.route
                            )
                        }
                    }
                )
            }
        }) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(
                    article = articles,
                    navigateToDetail = {
                        navigateToDetail(
                            navController = navController,
                            article = it
                        )
                    }
                )
            }
            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val result = viewModel.result.value
                BackHandler(true) {
                    navigateTo(navController, Route.HomeScreen.route)
                }
                SearchScreen(
                    result = result,
                    event = viewModel::event,
                    navigateToDetail = {
                        navigateToDetail(
                            navController = navController,
                            article = it
                        )
                    }
                )
            }
            composable(route = Route.DetailScreen.route) {
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
                    ?.let { article ->
                        DetailScreen(
                            article = article,
                            event = {},
                            onBackClick = {navController.navigateUp()}
                        )
                    }
            }
            composable(route = Route.BookmarksScreen.route) {
                BookmarksScreen()
            }
            composable(route = Route.ProfileScreen.route) {
                ProfileScreen()
            }
        }
    }
}

private fun navigateTo(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun navigateToDetail(navController: NavController, article: Article){
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(
        route = Route.DetailScreen.route
    )
}