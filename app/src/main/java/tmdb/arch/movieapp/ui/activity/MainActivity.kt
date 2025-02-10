package tmdb.arch.movieapp.ui.activity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import tmdb.arch.movieapp.compose.theme.TmdbTheme
import tmdb.arch.movieapp.domain.usecases.GetSavedMoviesUseCase
import tmdb.arch.movieapp.ui.screens.details.MovieDetailsScreen
import tmdb.arch.movieapp.ui.screens.details.MovieDetailsViewModel
import tmdb.arch.movieapp.ui.screens.discover.DiscoverMoviesScreen
import tmdb.arch.movieapp.ui.screens.discover.DiscoverMoviesViewModel
import tmdb.arch.movieapp.ui.screens.saved.SavedMoviesScreen
import tmdb.arch.movieapp.ui.screens.saved.SavedMoviesViewModel
import tmdb.arch.movieapp.ui.screens.search.SearchMoviesScreen
import tmdb.arch.movieapp.ui.screens.search.SearchMoviesViewModel

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TmdbTheme {
                val navController = rememberNavController()

                Surface {
                    NavHost(
                        navController = navController,
                        startDestination = "discover"
                    ) {
                        composable("discover") {
                            val viewModel = koinViewModel<DiscoverMoviesViewModel>()
                            DiscoverMoviesScreen(
                                viewModel = viewModel,
                                navController = navController
                            )
                        }
                        composable("search") {
                            val viewModel = koinViewModel<SearchMoviesViewModel>()
                            SearchMoviesScreen(
                                viewModel = viewModel,
                                navController = navController
                            )
                        }
                        composable(
                            route = "saved/{cmd}",
                            arguments = listOf(navArgument("cmd") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val arg =
                                backStackEntry.arguments?.getString("cmd") ?: return@composable
                            val viewModel = koinViewModel<SavedMoviesViewModel> {
                                parametersOf(
                                    GetSavedMoviesUseCase.Cmd.valueOf(arg)
                                )
                            }
                            SavedMoviesScreen(
                                viewModel = viewModel,
                                navController = navController
                            )
                        }
                        composable(
                            route = "details/{movieId}",
                            arguments = listOf(navArgument("movieId") { type = NavType.LongType })
                        ) { backStackEntry ->
                            val arg =
                                backStackEntry.arguments?.getLong("movieId") ?: return@composable
                            val viewModel = koinViewModel<MovieDetailsViewModel> {
                                parametersOf(arg)
                            }
                            MovieDetailsScreen(
                                viewModel = viewModel

                            )
                        }

                    }
                }
            }

        }

    }
}
