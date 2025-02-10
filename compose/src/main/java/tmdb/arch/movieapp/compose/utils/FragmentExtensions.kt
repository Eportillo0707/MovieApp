package tmdb.arch.movieapp.compose.utils

import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import tmdb.arch.movieapp.compose.theme.TmdbTheme


fun Fragment.compose(
    content: @Composable () -> Unit
): View {

    return ComposeView(requireContext()).apply {
        setContent {
            TmdbTheme {
                content()
            }
        }
    }
}