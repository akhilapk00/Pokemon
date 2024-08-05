package com.pokemon.app.ui.utils

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.pokemon.app.network.model.Type
import com.pokemon.app.ui.theme.TypeBug
import com.pokemon.app.ui.theme.TypeDark
import com.pokemon.app.ui.theme.TypeDragon
import com.pokemon.app.ui.theme.TypeElectric
import com.pokemon.app.ui.theme.TypeFairy
import com.pokemon.app.ui.theme.TypeFighting
import com.pokemon.app.ui.theme.TypeFire
import com.pokemon.app.ui.theme.TypeFlying
import com.pokemon.app.ui.theme.TypeGhost
import com.pokemon.app.ui.theme.TypeGrass
import com.pokemon.app.ui.theme.TypeGround
import com.pokemon.app.ui.theme.TypeIce
import com.pokemon.app.ui.theme.TypeNormal
import com.pokemon.app.ui.theme.TypePoison
import com.pokemon.app.ui.theme.TypePsychic
import com.pokemon.app.ui.theme.TypeRock
import com.pokemon.app.ui.theme.TypeSteel
import com.pokemon.app.ui.theme.TypeWater
import java.util.Locale
import kotlin.random.Random


fun getRandomLightColor(): Color {
    // Generate random values for RGB channels
    val red = Random.nextInt(200, 256) // Light red range
    val green = Random.nextInt(200, 256) // Light green range
    val blue = Random.nextInt(200, 256) // Light blue range

    return Color(red, green, blue)
}

fun parseTypeToColor(type: Type?): Color {
    return when(type?.type?.name?.toLowerCase(Locale.ROOT)) {
        "normal" -> TypeNormal
        "fire" -> TypeFire
        "water" -> TypeWater
        "electric" -> TypeElectric
        "grass" -> TypeGrass
        "ice" -> TypeIce
        "fighting" -> TypeFighting
        "poison" -> TypePoison
        "ground" -> TypeGround
        "flying" -> TypeFlying
        "psychic" -> TypePsychic
        "bug" -> TypeBug
        "rock" -> TypeRock
        "ghost" -> TypeGhost
        "dragon" -> TypeDragon
        "dark" -> TypeDark
        "steel" -> TypeSteel
        "fairy" -> TypeFairy
        else -> Color.Black
    }
}

@Composable
fun SetStatusBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(color)
    }
}