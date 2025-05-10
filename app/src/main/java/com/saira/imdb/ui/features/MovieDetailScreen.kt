package com.saira.imdb.ui.features

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.saira.imdb.R
import com.saira.imdb.domain.model.Movie
import com.saira.imdb.utils.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(navController: NavController, movie: Movie) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF162936))
    ) {

        MoviePoster(movie.imageUrl,movie.title)

        Spacer(modifier = Modifier.height(8.dp))

        MovieGenre(movie.genres)

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(vertical = 10.dp,
                    horizontal = 10.dp)
        ) {
            MovieAverageRating( "%.1f".format(movie.rating))

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = movie.startYear.toString(),
                style = TextStyle(fontSize = 25.sp, color = Color.White, fontWeight = Bold)
            )

            Spacer(modifier = Modifier.width(10.dp))

            MovieRatingIcon(movie.isAdult)

            Spacer(modifier = Modifier.width(10.dp))

            MovieDuration(movie.runtimeMinutes)
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = Constants.SUMMARY_TXT,
            modifier = Modifier
                .padding(horizontal = 10.dp,
                    vertical = 10.dp),
            style = TextStyle(fontSize = 23.sp, color = Color(0xFFf3ce13), fontWeight = Bold)
        )

        Text(
            text = movie.description.toString(),
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(horizontal = 10.dp),
            style = TextStyle(fontSize = 18.sp, color = Color.White)
        )

    }
}

@Composable
fun MoviePoster(imageUrl: String?, title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .placeholder(R.drawable.movie_placeholder)
                .crossfade(true)
                .build(),
            contentDescription = title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        // Gradient Overlay
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .align(Alignment.BottomCenter)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
        )

        // Text on top of gradient
        Text(
            text = title,
            color = Color.White,
            style = TextStyle(fontSize = 30.sp, fontWeight = Bold),
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(12.dp)
        )
    }
}

@Composable
fun MovieGenre(genres: List<String>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        genres.forEach { genre ->
            Surface(
                color = Color(0xFFf3ce13),
                shape = RoundedCornerShape(16.dp),
                tonalElevation = 4.dp
            ) {
                Text(
                    text = genre,
                    color = Color.Black,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun MovieAverageRating(rating: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(8.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.icon_star),
            contentDescription = "Star Icon",
            tint = Color.Unspecified,
            modifier = Modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.width(5.dp))

        Text(
            text = rating,
            style = TextStyle(fontSize = 25.sp, color = Color.White, fontWeight = Bold)
        )
    }

}

@Composable
fun MovieRatingIcon(isAdult : Boolean) {
    if (isAdult) {
        Icon(
            painter = painterResource(id = R.drawable.icon_mature),
            contentDescription = "Mature Icon",
            tint = Color.Unspecified,
            modifier = Modifier.size(30.dp)
        )
    } else {
        Icon(
            painter = painterResource(id = R.drawable.icon_general),
            contentDescription = "General Icon",
            tint = Color.Unspecified,
            modifier = Modifier.size(30.dp)
        )
    }
}

@Composable
fun formatDuration(minutes: Int?): String {
    val hours = minutes?.div(60)
    val remainingMinutes = minutes?.rem(60)
    return "${hours}h ${remainingMinutes}m"
}

@Composable
fun MovieDuration(runtimeMinutes : Int?){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(8.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.icon_duration),
            contentDescription = "clock",
            tint = Color.Unspecified,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(5.dp))

        Text(
            text = formatDuration(runtimeMinutes),
            style = TextStyle(fontSize = 25.sp, color = Color.White, fontWeight = Bold)
        )
    }

}