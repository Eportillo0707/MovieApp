package tmdb.arch.movieapp.compose.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun TmdbActionButtons(
    icon: Painter,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.background(
            color = if (isSelected) {
                MaterialTheme.colorScheme.tertiaryContainer
            } else {
                MaterialTheme.colorScheme.secondaryContainer
            },
            shape = RoundedCornerShape(10.dp)
        ).clickable { onClick() }
    ) {

        Icon(
            painter = icon,
            contentDescription = null,
            tint = if (isSelected) {
                MaterialTheme.colorScheme.onTertiaryContainer
            } else {
                MaterialTheme.colorScheme.onSecondaryContainer
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(32.dp)
                .padding(top = 5.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = if (isSelected) {
                MaterialTheme.colorScheme.onTertiaryContainer
            } else {
                MaterialTheme.colorScheme.onSecondaryContainer
            },
            modifier = Modifier.padding(5.dp)
                .align(Alignment.CenterHorizontally)
        )


    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TmdbActionButtonsPreview() {
    TmdbTheme {
        Column {
            TmdbActionButtons(
                icon = painterResource(id = R.drawable.ic_eye),
                text = "To Watch",
                isSelected = true,
                onClick = {}

            )

            TmdbActionButtons(
                icon = painterResource(id = R.drawable.ic_eye),
                text = "To Watch",
                isSelected = false,
                onClick = {}

            )
        }
    }
}