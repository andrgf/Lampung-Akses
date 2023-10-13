package com.example.newsapp.presentation.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.example.newsapp.R
import com.example.newsapp.data.remote.dto.Article
import com.example.newsapp.presentation.screen.home.component.ArticleList
import com.example.newsapp.presentation.screen.home.component.HomeTopBar
import com.example.newsapp.ui.theme.WhiteGray

@Composable
fun HomeScreen(
    article: LazyPagingItems<Article>,
    navigateToDetail:(Article)-> Unit
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 4.dp, bottom = 4.dp)
        .statusBarsPadding()
    ) {
        HomeTopBar()

        ArticleList(
            articles = article,
            onClick = navigateToDetail
        )
    }
}