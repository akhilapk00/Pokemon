package com.pokemon.app.ui.screens.pokemon.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.pokemon.app.data.BundleArguments
import com.pokemon.app.network.model.Pokemon
import com.pokemon.app.state.ResponseState
import com.pokemon.app.ui.NavRoutes
import com.pokemon.app.ui.screens.pokemon.detail.PokemonDetailScreen
import com.pokemon.app.ui.theme.Orange
import com.pokemon.app.ui.theme.Pale_grey
import com.pokemon.app.ui.utils.SetStatusBarColor
import com.pokemon.app.ui.utils.TopBar

@Composable
fun PokemonListScreen(
    modifier: Modifier = Modifier,
    viewModel: PokemonListViewModel = hiltViewModel(),
    navController: NavHostController
) {
    LaunchedEffect(Unit){
        viewModel.loadPokemon()
    }
    Scaffold(topBar = { TopBar(title = "Pokemon list") }) { paddingValues ->
        val state = viewModel.pokemanListResponseState.collectAsState().value
        when(state) {
           is ResponseState.Loading -> {
               Box(Modifier.fillMaxSize()) {
                   CircularProgressIndicator(modifier = Modifier.align(Alignment.Center), color = Color.White)
               }
            }
            is ResponseState.Failed -> {

            }
            is ResponseState.Success -> {
                SetStatusBarColor(color = Color.Black)
                LazyColumn(
                    contentPadding = paddingValues,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                listOf(
                                    Color.Black,
                                    Orange
                                )
                            )
                        ),

                    ) {
                    items(viewModel.pokemonList) { item ->
                        ListItem(item,navController)
                    }
                }
            }

            else -> {}
        }

    }
}

@Composable
fun ListItem(item: Pokemon, navController: NavHostController) {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .background(color = Color.White, shape = RoundedCornerShape(10.dp))
            .padding(5.dp)
    ) {
        Text(
            text = item.name,
            style = TextStyle(
                fontSize = 15.sp,
                color = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable {
                    navController.navigate("PokemonDetailScreen?pokemon=${item.getPath()}")
                }
        )
    }

}