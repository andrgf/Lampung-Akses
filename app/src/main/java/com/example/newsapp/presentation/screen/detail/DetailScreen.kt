package com.example.newsapp.presentation.screen.detail

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsapp.data.remote.dto.Article
import com.example.newsapp.data.remote.dto.Source
import com.example.newsapp.presentation.screen.detail.component.DetailTopBar
import com.example.newsapp.ui.theme.WhiteGray
import com.example.newsapp.util.dateFormat

@Composable
fun DetailScreen(
    article: Article,
    event:(DetailEvent) -> Unit,
    onBackClick:() -> Unit
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        DetailTopBar(
            onBookmarksClick = {
                event(DetailEvent.SaveArticle)
            },
            onBackClick = onBackClick
        )
        
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
            contentPadding = PaddingValues(
                start = 4.dp,
                end = 4.dp,
                top = 2.dp
            )
        ) {
            item {
                Text(
                    text = article.author,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = article.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = dateFormat(article.publishedAt),
                    fontSize = 12.sp,
                    color = WhiteGray
                )
                Spacer(modifier = Modifier.height(5.dp))
                AsyncImage(
                    model = ImageRequest.Builder(context = context).data(article.urlToImage).build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = article.description,
                    fontSize = 14.sp
                )
            }
        }
    }


}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DetailScreenPreview() {
    DetailScreen(
        article = Article(
            author = "",
            content = "",
            description= "Description",
            publishedAt = "12 12 2003",
            source = Source(
                id = "", name = "Lampung"
            ),
            title = "Indonesia Merdeka",
            url = "",
            urlToImage = ""
        ),
        event = { }
    ) {

    }

}