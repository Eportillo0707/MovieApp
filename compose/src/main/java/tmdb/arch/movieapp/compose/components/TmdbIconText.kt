package tmdb.arch.movieapp.compose.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tmdb.arch.movieapp.compose.R
import tmdb.arch.movieapp.compose.theme.TmdbTheme

@Composable
fun TmdbIconText(
    painter: Painter,
    text: String,
    modifier: Modifier = Modifier
){

    Row(modifier = modifier) {
        Icon(
            painter = painter,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.size(12.dp)
                .align(Alignment.CenterVertically)
        )

        Spacer(modifier = Modifier.width(6.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TmdbIconTextPreview() {
    TmdbTheme {
        TmdbIconText(
            painter = painterResource(id = R.drawable.ic_globe),
            text = "English",
            modifier = Modifier.padding(vertical = 10.dp)
        )
    }
}