package com.example.pokemon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pokemon.screens.pokemon_list_screen.pokemonListScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    screenTitle: NavDestination?,
    onBackClick: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = "Pokemon App",
                modifier = Modifier
                    .padding(start = 6.dp ,end = 6.dp)
                ,
            )

        },
        navigationIcon = {
            if (screenTitle?.route != pokemonListScreen){
                IconButton(onClick = onBackClick) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = null,
                        tint = Black,
                    )
                }

            }
        },
        actions = {

        }
    )
}

