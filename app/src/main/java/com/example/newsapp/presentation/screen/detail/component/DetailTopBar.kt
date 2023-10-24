package com.example.newsapp.presentation.screen.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(
    onBookmarksClick:() -> Unit,
    onBackClick:() -> Unit,
    onBrowserClick:() -> Unit
) {
    
    TopAppBar(
        title = { },
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = Color.Black,
            navigationIconContentColor = Color.Black
        ),
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
        },
        actions = {
            Row {
                IconButton(
                    onClick = onBookmarksClick
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.bookmarks),
                        contentDescription = null,

                    )
                }
                IconButton(
                    onClick = onBrowserClick,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.browser),
                        contentDescription = null,
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DetailTopBarPreview() {
    DetailTopBar(
        onBookmarksClick = { /*TODO*/ },
        onBackClick = { },
        onBrowserClick = { }
    )
}