package com.pokemon.app.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pokemon.app.R
import com.pokemon.app.ui.theme.Pale_grey

@Composable
fun TopBar(title: String) {
    Box(
        modifier = Modifier
            .defaultMinSize(minHeight = 55.dp)
            .fillMaxWidth()
            .background(color = Color.Black)
            .padding(horizontal = 15.dp)
    ) {

        Text(
            text = title,

            modifier = Modifier
                .align(Alignment.Center),
            style = TextStyle(color = Color.White, fontSize = 15.sp),
            onTextLayout = {}
        )

    }
}