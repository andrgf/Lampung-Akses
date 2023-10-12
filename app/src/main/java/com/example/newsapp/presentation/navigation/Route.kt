package com.example.newsapp.presentation.navigation

sealed class Route(
    val route: String
) {
    object OnBoardingScreen: Route(route = "onBoarding")
    object HomeScreen: Route(route = "home")
    object SearchScreen: Route(route = "search")
    object BookmarksScreen: Route(route = "bookmarks")
    object DetailScreen: Route(route = "detail")
    object ProfileScreen: Route(route = "profile")
    object AppStartNavigation: Route(route = "appStartNavigation")
    object NewsNavigation: Route(route = "newsNavigation")
    object NewsNavigator: Route(route = "newsNavigator")
}
