package com.pokemon.app.ui.screens.pokemon.detail

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.pokemon.app.R
import com.pokemon.app.network.model.PokmonDetail
import com.pokemon.app.network.model.Sprites
import com.pokemon.app.network.model.Stat
import com.pokemon.app.network.model.Type
import com.pokemon.app.state.ResponseState
import com.pokemon.app.ui.utils.SetStatusBarColor
import com.pokemon.app.ui.utils.getRandomLightColor
import com.pokemon.app.ui.utils.horizondalSpace
import com.pokemon.app.ui.utils.parseTypeToColor
import com.pokemon.app.ui.utils.verticalSpace
import java.lang.Math.round
import java.util.Locale

@Composable
fun PokemonDetailScreen(

    modifier: Modifier = Modifier,
    pokemon: String,
    viewmodel: PokemonDetailViewmodel = hiltViewModel(),
    navHostController: NavHostController
) {
    LaunchedEffect(Unit) {
        viewmodel.loadPokMonDetail(pokemon)
    }
    Scaffold(modifier = modifier,
    ) { paddingValues ->


        var state = viewmodel.pokemanDetailResponseState.collectAsState().value
        when (state) {
            is ResponseState.Loading -> {
                Box(Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center), color = Color.White)
                }
            }

            is ResponseState.Failed -> {

            }

            is ResponseState.Success -> {
                val data = state.response
                data?.sprites?.back_default
                viewmodel.themeColor = parseTypeToColor(data?.types?.first())
                SetStatusBarColor(viewmodel.themeColor)
                showBody(data, modifier, paddingValues)
            }

            else -> {}
        }

    }
}

@Composable
fun showBody(data: PokmonDetail?, modifier: Modifier, paddingValues: PaddingValues) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(paddingValues)
            .background(
                Brush.verticalGradient(
                    listOf(
                        parseTypeToColor((data?.types?.first())),
                        Color.Transparent
                    )
                )
            )
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            parseTypeToColor((data?.types?.first())),
                            Color.Transparent
                        )
                    )
                )
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 100.dp, start = 20.dp, end = 20.dp, bottom = 60.dp)
                    .background(Color.White, shape = RoundedCornerShape(10.dp))
                     .padding(bottom = 30.dp)
                    .align(Alignment.TopCenter)

            ) {
                showContents(data, paddingValues, modifier)
            }

            LoadImageFromUrl(
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.TopCenter),
                imageUrl = data?.sprites?.front_default ?: ""
            )
        }
    }
}

@Composable
fun showContents(data: PokmonDetail?, paddingValues: PaddingValues, modifier: Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState()),
    ) { verticalSpace(space = 40)

        Text(
            text = data?.name?.capitalize(Locale.ROOT) ?: "",
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontFamily = FontFamily(
                Font(
                    resId = R.font.jakarta_sans_semibold_600,
                    weight = FontWeight.W600
                )
            ),
            style = TextStyle(color = Color.Black)
        )
        verticalSpace(space = 10)
        data?.types?.let { PokemonTypeSection(it) }

        verticalSpace(space = 10)
        showTabsAndContents(data, modifier, paddingValues)
    }


}

@Composable
fun PokemonTypeSection(types: List<Type>) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(16.dp)
    ) {
        for(type in types) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
                    .clip(CircleShape)
                    .background(parseTypeToColor(type))
                    .height(35.dp)
            ) {
                Text(
                    text = type.type.name.capitalize(Locale.ROOT),
                    color = Color.White,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun showTabsAndContents(data: PokmonDetail?, modifier: Modifier, paddingValues: PaddingValues,
                        viewmodel: PokemonDetailViewmodel = hiltViewModel()) {
    var tabIndex by remember { mutableStateOf(0) }
    val pagerState = rememberPagerState(initialPage = tabIndex, pageCount = {
        DetailEnum.values().size

    })

    Column(
        modifier = modifier.fillMaxWidth()

    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 5.dp)
        ) {
            DetailEnum.values().forEachIndexed { index, title ->

                Box(
                    modifier = modifier
                        .padding(10.dp)
                        .background(
                            color = if (index == tabIndex) viewmodel.themeColor else Color.White,
                            shape = RoundedCornerShape(5.dp)
                        )
                        .border(0.5.dp, viewmodel.themeColor, RoundedCornerShape(5.dp))
                ) {
                    Text(
                        text = title.value,
                        modifier = Modifier
                            .padding(5.dp)
                            .clickable {
                                tabIndex = index
                            },
                        style = TextStyle(color = if (index == tabIndex) Color.White else Color.Black)
                    )
                }


            }
        }


        HorizontalPager(pagerState) {

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center)
            {
                getView(tabIndex, data, paddingValues)
            }


        }
    }
}

@Composable
fun getView(tabIndex: Int, data: PokmonDetail?, paddingValues: PaddingValues) {
    val enum = DetailEnum.values()[tabIndex]

    when (enum) {
        DetailEnum.ABOUT -> {
            PokemonDetailDataSection(data?.weight?.toInt()?:0,data?.height?.toInt()?:0)
        }

        DetailEnum.EVOLUTION -> {
            data?.sprites?.let {
                FixedGridLayout(it.allImageUrls())
            }
        }

        DetailEnum.BASE_STATS -> {
            if (data != null) {
                PokemonBaseStats(data)
            }
        }

        else -> {}
    }
}
@Composable
fun PokemonDetailDataSection(
    pokemonWeight: Int,
    pokemonHeight: Int,
    sectionHeight: Dp = 80.dp
) {
    val pokemonWeightInKg = remember {
        round(pokemonWeight * 100f) / 1000f
    }
    val pokemonHeightInMeters = remember {
        round(pokemonHeight * 100f) / 1000f
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        PokemonDetailDataItem(

            dataValue = pokemonWeightInKg,
            dataUnit = "kg",
            modifier = Modifier.weight(1f),
            dataName = "Weight"
        )
        Spacer(modifier = Modifier
            .size(1.dp, sectionHeight)
            .background(Color.LightGray))
        PokemonDetailDataItem(
            dataValue = pokemonHeightInMeters,
            dataUnit = "m",

            modifier = Modifier.weight(1f),
            dataName = "Height"
        )
    }
}

@Composable
fun PokemonDetailDataItem(
    dataValue: Float,
    dataUnit: String,
    dataName:String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = dataName,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "$dataValue$dataUnit",
            color = Color.Black
        )
    }
}



@Composable
fun PokemonBaseStats(
    pokemonInfo: PokmonDetail,
    animDelayPerItem: Int = 100,
    viewmodel: PokemonDetailViewmodel = hiltViewModel()
) {
    val maxBaseStat = remember {
        pokemonInfo.stats.maxOf{ it.base_stat }
    }
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {


        Spacer(modifier = Modifier.height(4.dp))

        for(i in pokemonInfo.stats.indices) {
            val stat = pokemonInfo.stats[i]
            PokemonStat(
                statName = stat.stat?.name?:"",
                statValue = stat.base_stat.toInt(),
                statMaxValue = maxBaseStat.toInt(),
                statColor = viewmodel.themeColor,
                animDelay = i * animDelayPerItem
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}


@Composable
fun PokemonStat(
    statName: String,
    statValue: Int,
    statMaxValue: Int,
    statColor: Color,
    height: Dp = 28.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }
    val curPercent = animateFloatAsState(
        targetValue = if(animationPlayed) {
            statValue / statMaxValue.toFloat()
        } else 0f,
        animationSpec = tween(
            animDuration,
            animDelay
        )
    )
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .clip(CircleShape)
            .background(
                if (isSystemInDarkTheme()) {
                    Color(0xFF505050)
                } else {
                    Color.LightGray
                }
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(curPercent.value)
                .clip(CircleShape)
                .background(statColor)
                .padding(horizontal = 8.dp)
        ) {
            Text(
                text = statName,
                fontWeight = FontWeight.Bold,
                style = TextStyle(color = Color.Black)
            )
            Text(
                text = (curPercent.value * statMaxValue).toInt().toString(),
                fontWeight = FontWeight.Bold,
                style = TextStyle(color = Color.Black)
            )
        }
    }
}



@Composable
fun FixedGridLayout(forms: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        forms.chunked(2).forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),

                ) {
                rowItems.forEach { imageUrl ->
                    ImageBox(imageUrl)
                }
            }
        }
    }
}

@Composable
fun ImageBox(imageUrl: String,viewmodel: PokemonDetailViewmodel = hiltViewModel()) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(color = viewmodel.themeColor, shape = RoundedCornerShape(50.dp))
    ) {
        LoadImageFromUrl(imageUrl = imageUrl, modifier = Modifier.size(100.dp))
    }
}



@Composable
fun LoadImageFromUrl(
    imageUrl: String,
    modifier: Modifier
) {
    Image(
        painter = rememberAsyncImagePainter(imageUrl),
        contentDescription = null,
        modifier = modifier
    )
}

enum class DetailEnum(val value: String) {
    ABOUT("About"),
    EVOLUTION("Evolution"),
    BASE_STATS("Base stats")
}
