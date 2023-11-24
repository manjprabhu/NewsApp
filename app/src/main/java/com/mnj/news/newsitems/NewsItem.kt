package com.mnj.news.newsitems

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.mnj.news.model.NewsModel

@OptIn(ExperimentalCoilApi::class)
@Composable
fun NewsItem(news: NewsModel, onClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { news.url?.let { onClick(it) } },
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        backgroundColor = Color.Red
    ) {
        Surface {
            Row(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()
            ) {

                news.urlToImage?.let {
                    Image(
                        painter = rememberImagePainter(
                            data = it
                        ),
                        contentDescription = news.description,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )
                }
                Spacer(Modifier.width(5.dp))
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.7f)
                ) {
                    Text(
                        text = news.title,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 3,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(Modifier.height(5.dp))
                    news.publishedAt?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodySmall,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.align(alignment = Alignment.End)
                        )
                    }
                }
            }
        }
    }
}
