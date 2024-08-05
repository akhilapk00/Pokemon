package com.pokemon.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pokemon.app.data.BundleArguments
import com.pokemon.app.ui.NavRoutes
import com.pokemon.app.ui.screens.pokemon.detail.PokemonDetailScreen
import com.pokemon.app.ui.screens.pokemon.list.PokemonListScreen
import com.pokemon.app.ui.screens.splash.SplashScreen
import com.pokemon.app.ui.theme.PokemonTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, NavRoutes.SPLASH) {
                    composable(NavRoutes.SPLASH) {
                        SplashScreen(navController = navController)

                    }
                    composable(NavRoutes.POKEMON_LIST) {
                        PokemonListScreen(navController = navController)
                    }

                    composable("${NavRoutes.POKE_MON_DETAIL}?${BundleArguments.POKEMON_PATH}={${BundleArguments.POKEMON_PATH}}",
                        arguments = listOf(navArgument(BundleArguments.POKEMON_PATH) { type = NavType.StringType })
                    ) { backStackEntry ->
                        val pokemon = backStackEntry.arguments?.getString(BundleArguments.POKEMON_PATH) ?: ""
                        PokemonDetailScreen(navHostController = navController, pokemon = pokemon)
                    }
                }


            }


        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PokemonTheme {
        Greeting("Android")
    }
}