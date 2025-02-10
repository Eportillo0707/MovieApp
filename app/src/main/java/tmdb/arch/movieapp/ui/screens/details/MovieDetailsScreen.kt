package tmdb.arch.movieapp.ui.screens.details

import android.content.res.Configuration
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.arch.utils.UiState
import tmdb.arch.movieapp.BuildConfig
import tmdb.arch.movieapp.compose.R
import tmdb.arch.movieapp.compose.components.TmdbActionButtons
import tmdb.arch.movieapp.compose.components.TmdbError
import tmdb.arch.movieapp.compose.components.TmdbIconText
import tmdb.arch.movieapp.compose.components.TmdbImageLoadingError
import tmdb.arch.movieapp.compose.components.TmdbLoading
import tmdb.arch.movieapp.compose.theme.TmdbTheme
import tmdb.arch.movieapp.repository.models.Movie

@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel
) {
    val state by viewModel.movie.collectAsState()

    ScreenContent(
        state = state,
        onToWatchClicked = viewModel::onToWatchButtonClicked,
        onFavoriteClicked = viewModel::onFavoriteButtonClicked,
        onRetryClicked = viewModel::onRetryClicked
    )
}

@Composable
private fun ScreenContent(
    state: UiState<Movie>,
    onRetryClicked: () -> Unit,
    onFavoriteClicked: () -> Unit,
    onToWatchClicked: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (state) {
            UiState.Loading -> {
                TmdbLoading(modifier = Modifier.align(Alignment.Center))
            }

            UiState.Error -> {
                TmdbError(onRetryClicked)
            }

            is UiState.Result -> {
                MovieContent(
                    movie = state.item,
                    onToWatchClicked = onToWatchClicked,
                    onFavoriteClicked = onFavoriteClicked
                )
            }
        }
    }
}

@Composable
private fun MovieContent(
    movie: Movie,
    onToWatchClicked: () -> Unit,
    onFavoriteClicked: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        SubcomposeAsyncImage(
            model = BuildConfig.IMAGE_URL + movie.posterPath,
            contentScale = ContentScale.Crop,
            contentDescription = "Movie poster",
            loading = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = MaterialTheme.colorScheme.tertiaryContainer
                        )
                ) {
                    TmdbLoading(modifier = Modifier.align(Alignment.Center))
                }
            },
            error = {
                TmdbImageLoadingError()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
        )
        Text(
            text = movie.title.toString(),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 20.dp)
        )
        if (movie.originalLanguage != null) {
            TmdbIconText(
                painter = painterResource(id = R.drawable.ic_globe),
                text = "Language: ${movie.originalLanguage}",
                modifier = Modifier.padding(start = 20.dp)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        if (movie.voteAverage != null) {
            TmdbIconText(
                painter = painterResource(id = R.drawable.ic_star),
                text = "Rating: ${movie.voteAverage} / 10 TMDB",
                modifier = Modifier.padding(start = 20.dp)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        if (movie.runtime != null) {

            val hours = movie.runtime!! / 60
            val mins = movie.runtime!! % 60

            TmdbIconText(
                painter = painterResource(id = R.drawable.ic_clock),
                text = "Duration: $hours h $mins m",
                modifier = Modifier.padding(start = 20.dp)
            )

        }
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(20.dp)
        ) {
            TmdbActionButtons(
                icon = painterResource(id = R.drawable.ic_eye),
                text = "To Watch",
                isSelected = movie.isToWatch,
                onClick = onToWatchClicked,
                modifier = Modifier.size(70.dp)

            )
            Spacer(modifier = Modifier.width(20.dp))
            TmdbActionButtons(
                icon = painterResource(id = R.drawable.ic_star),
                text = "Favored",
                isSelected = movie.isFavored,
                onClick = onFavoriteClicked,
                modifier = Modifier.size(70.dp)
            )
        }

        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(2.dp)
                )
        )
        Text(
            text = "Overview",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 10.dp)
        )

        Text(
            text = movie.overview ?: "",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ScreenContentPreview() {
    TmdbTheme {
        MovieContent(
            movie = Movie(
                title = "Movie title",
                id = 1231L,
                originalTitle = "Original title",
                originalLanguage = "English",
                overview = "Overview",
                voteAverage = "8.8",
                runtime = 120,
                genres = null
            ),
            onToWatchClicked = {},
            onFavoriteClicked = {}
        )
    }
}