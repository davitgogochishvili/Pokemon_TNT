package com.example.pokemon.screens.pokemon_image_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokemon.R
import com.example.pokemon.screens.pokemon_list_screen.LoadImageFromUrl
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun PokemonImageScreen(
    imageUrl : String,
    onBackClick : () -> Unit
){
    Column {
        Box(
            contentAlignment = Center,
            modifier = Modifier
                .shadow(5.dp, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .aspectRatio(1f)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Green,
                            Color.Cyan
                        )
                    )
                )
                .fillMaxSize(0.7f)

        ) {
            Column(Modifier.fillMaxSize()) {
                LoadImageFromUrl(url = decodeUrl(imageUrl)!! , defaultSize = 500.dp)


            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = { onBackClick() } , modifier = Modifier.fillMaxWidth().fillMaxHeight(0.1f)) {
            Text(text = stringResource(R.string.go_back))
        }
    }


}
fun decodeUrl(url : String): String? {
    val decodedUrl = URLDecoder.decode(url, StandardCharsets.UTF_8.toString())
    return decodedUrl
}