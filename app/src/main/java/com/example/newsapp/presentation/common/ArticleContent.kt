package com.example.newsapp.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.newsapp.data.remote.dto.Article
import com.example.newsapp.data.remote.dto.Source
import com.example.newsapp.util.dateFormat

@Composable
fun ArticleContent(
    article: Article,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .padding(start = 12.dp, end = 12.dp)
            .clickable { onClick?.invoke() }
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .weight(2f),
        ) {
            Text(
                text = article.author ?: "author",
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 6.dp),
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = article.title ?: "",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                modifier = Modifier.padding(bottom = 9.dp)
            )
            Text(
                text = dateFormat(article.publishedAt ?: ""),
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray
            )

        }

        AsyncImage(
            model = article.urlToImage,
            contentDescription = article.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(100.dp)
                .weight(1f)
                .clip(RoundedCornerShape(12.dp))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleListPreview() {
    ArticleContent(
        article = Article(
            author = "",
            content = "",
            description = "",
            publishedAt = "23-12-2001",
            source = Source("", "Detik.com"),
            title = "Jokowi akhirnya purna",
            url = "",
            urlToImage = ""
        )
    )
}