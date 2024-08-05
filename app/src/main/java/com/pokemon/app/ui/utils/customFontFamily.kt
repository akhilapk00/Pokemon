package com.pokemon.app.ui.utils

import android.graphics.fonts.Font
import android.graphics.fonts.FontFamily
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pokemon.app.R


@Composable
fun verticalSpace(space:Int){
    Spacer(modifier = Modifier.size(space.dp))
}

@Composable
fun horizondalSpace(space:Int){
    Spacer(modifier = Modifier.size(space.dp))
}