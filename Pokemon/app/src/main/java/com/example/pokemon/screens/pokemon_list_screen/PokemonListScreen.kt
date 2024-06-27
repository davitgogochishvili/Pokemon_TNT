package com.example.pokemon.screens.pokemon_list_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.pokemon.R
import com.example.pokemon.data.PokemonUiModel
import com.google.accompanist.coil.CoilImage
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun PokemonListScreen(
    onImageClick: (String) -> Unit,
    onDetailClick : (String,String) -> Unit,
    viewModel: PokemonListViewModel = hiltViewModel()
    ){

    val pokemonList by remember {
        viewModel.pokemonList
    }
    val endReached by remember { viewModel.endReached }
    val loadError by remember { viewModel.loadError }
    val isLoading by remember { viewModel.isLoading }

    Column(
        modifier =
        Modifier
            .fillMaxSize()
    ){
        LazyColumn {
            val itemCount = if(pokemonList.size % 2 == 0) {
                pokemonList.size / 2
            } else {
                pokemonList.size / 2 + 1
            }

            items(itemCount) {
                if(it >= itemCount - 1 && !endReached) {
                    viewModel.loadPokemonList()
                }
                PokedexRow(rowIndex = it, entries = pokemonList,viewModel,
                    navigateToImagePage = { url ->

                        onImageClick(encodeUrl(url))
                    },
                    onDetailsClicked = { name , image->
                        onDetailClick(name, encodeUrl(image))
                    }
                    )
            }
        }
        Box(
            contentAlignment = Center,
            modifier = Modifier.fillMaxSize()
        ) {
            if(isLoading) {
                CircularProgressIndicator(color = Blue)
            }
            if(loadError.isNotEmpty()) {
                RetrySection(error = loadError) {
                    viewModel.loadPokemonList()
                }
            }
        }

        }
    }
fun encodeUrl(url: String):String{
    val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
    return encodedUrl
}

@Composable
fun LoadImageFromUrl(url: String , defaultSize: Dp = 120.dp) {
    val painter = rememberImagePainter(
        data = url,
        builder = {
            crossfade(true)
        }
    )

    Image(
        painter = painter,
        contentDescription = null,
        modifier = Modifier.size(defaultSize),
        contentScale = ContentScale.Crop
    )

    
}
@Composable
fun PokedexEntry(
    entry: PokemonUiModel,
    modifier: Modifier = Modifier,
    navigateToImagePage : (String) -> Unit,
    onDetailsClicked : (String,String) -> Unit,
    viewModel: PokemonListViewModel
) {
    val defaultDominantColor = Gray
    var dominantColor by remember {
        mutableStateOf(defaultDominantColor)
    }

    Box(
        contentAlignment = Center,
        modifier = modifier
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(1f)
            .background(
                Brush.verticalGradient(
                    listOf(
                        dominantColor,
                        defaultDominantColor
                    )
                )
            )
            .clickable {
                navigateToImagePage(entry.imageUrl)
//                navController.navigate(
//                    "pokemon_detail_screen/${dominantColor.toArgb()}/${entry.pokemonName}"
//                )
            }
    ) {
        Column {
            LoadImageFromUrl(url = entry.imageUrl)
            Text(
                text = entry.pokemonName,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "Click for details >",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onDetailsClicked(entry.pokemonName, entry.imageUrl)
                    }
            )
        }
    }
}

@Composable
fun PokedexRow(
    rowIndex: Int,
    entries: List<PokemonUiModel>,
    viewModel: PokemonListViewModel,
    navigateToImagePage: (String) -> Unit,
    onDetailsClicked: (String,String) -> Unit
) {
    Column {
        Row {
            PokedexEntry(
                entry = entries[rowIndex * 2],
                modifier = Modifier.weight(1f),
                viewModel = viewModel,
                navigateToImagePage = navigateToImagePage,
                onDetailsClicked = onDetailsClicked

            )
            Spacer(modifier = Modifier.width(16.dp))
            if(entries.size >= rowIndex * 2 + 2) {
                PokedexEntry(
                    entry = entries[rowIndex * 2 + 1],
                    modifier = Modifier.weight(1f),
                    viewModel = viewModel,
                    navigateToImagePage = navigateToImagePage,
                    onDetailsClicked = onDetailsClicked
                )
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun RetrySection(
    error: String,
    onRetry: () -> Unit
) {
    Column {
        Text(error, color = Color.Red, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { onRetry() },
            modifier = Modifier.align(CenterHorizontally)
        ) {
            Text(text = stringResource(R.string.retry))
        }
    }
}
@Preview
@Composable
fun PreviewLoadImageFromUrl() {
    LoadImageFromUrl("https://example.com/image.png")
}