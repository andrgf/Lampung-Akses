package com.example.newsapp.presentation.screen.home.tabs

import androidx.compose.runtime.Composable
import com.example.newsapp.presentation.navigation.Route

typealias ComposableFun = @Composable () -> Unit
sealed class TabsItem(var title: String) {
    object Everything: TabsItem(title = "Everything")
    object Viral: TabsItem(title = "Viral")
}