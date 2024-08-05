package com.pokemon.app.ui.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.pokemon.app.ui.NavRoutes
import com.pokemon.app.ui.theme.Orange
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    splashViewModel: SplashScreenViewModel = hiltViewModel()
){
//    var isLoading by remember { mutableStateOf(Boolean) }
    LaunchedEffect(Unit) {
        delay(3000)  // Splash screen delay for 3 seconds
//        isLoading = false
        navController.navigate(NavRoutes.POKEMON_LIST) {
            popUpTo("splash") { inclusive = true }
        }
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize() .background(
                Brush.verticalGradient(
                    listOf(
                        Color.Black,
                        Orange
                    )
                )
                )
    ) {
        Text(
           text = "POKEMON",
                   fontWeight = FontWeight.Bold,


            style = TextStyle(color = Color.White, fontSize = 20.sp),

        )
    }
}