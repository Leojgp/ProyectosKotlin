package com.leojgp.verticalpager.uiview

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leojgp.verticalpager.model.Movie
import com.leojgp.verticalpager.retrofit.ApiService
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

const val API_KEY = "79a1f8592eb117b911cb6d33f9639de1"

val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InfiniteVerticalPager() {
    val movies = remember { mutableStateListOf<Movie>() }
    var isLoading by remember { mutableStateOf(false) }
    val pagerState = rememberPagerState(pageCount = { movies.size })
    val scope = rememberCoroutineScope()
    var page by remember { mutableStateOf(1) }
    var totalPages by remember { mutableStateOf(Int.MAX_VALUE) }
    val apiService = remember { ApiService.create() }

    LaunchedEffect(Unit) {
        totalPages = loadMoreMovies(movies, page, apiService) ?: Int.MAX_VALUE
    }

    LaunchedEffect(pagerState.currentPage) {
        if (pagerState.currentPage >= movies.size - 3 && !isLoading && page < totalPages) {
            isLoading = true
            scope.launch {
                page++
                totalPages = loadMoreMovies(movies, page, apiService) ?: totalPages
                isLoading = false
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        VerticalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            val movie = movies[page]
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = movie.title,
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = movie.overview,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            )
        }
    }
}

suspend fun loadMoreMovies(movies: MutableList<Movie>, page: Int, apiService: ApiService): Int? {
    return try {
        val responseString = apiService.getNowPlaying(API_KEY, "es-ES", page)
        val movieResponse = responseString

        movies.addAll(movieResponse.results)
        movieResponse.totalPages
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPager() {
    InfiniteVerticalPager()
}
