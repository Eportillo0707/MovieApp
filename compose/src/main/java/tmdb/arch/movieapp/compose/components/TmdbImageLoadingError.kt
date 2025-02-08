package tmdb.arch.movieapp.compose.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import tmdb.arch.movieapp.compose.R
import tmdb.arch.movieapp.compose.theme.TmdbTheme

@Composable
fun TmdbImageLoadingError() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,

                )
    ){
        Icon(
            painter = painterResource(id = R.drawable.ic_error),
            contentDescription = "Error loading image",
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ImageLoadingErrorPreview() {
    TmdbTheme {
        TmdbImageLoadingError()
    }
}
