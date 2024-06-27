package com.example.pokemon.navigation

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import com.example.pokemon.screens.pokemon_detail_screen.navigateToPokemonDetailScreen
import com.example.pokemon.screens.pokemon_detail_screen.pokemonDetail
import com.example.pokemon.screens.pokemon_image_screen.navigateToPokemonImageScreen
import com.example.pokemon.screens.pokemon_image_screen.pokemonImage
import com.example.pokemon.screens.pokemon_list_screen.navigateToPokemonList
import com.example.pokemon.screens.pokemon_list_screen.pokemonList
import com.example.pokemon.screens.pokemon_list_screen.pokemonListScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = pokemonListScreen,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ){
        pokemonList(
            onImageClick =  {
                navController.navigateToPokemonImageScreen(imageUrl = it)
            },
            onDetailClick = { name , image ->
                navController.navigateToPokemonDetailScreen(pokemonName = name , pokemonImage = image)
            }
        )
        pokemonDetail {
            navController.navigateToPokemonList()
        }

        pokemonImage {
            navController.navigateToPokemonList()
        }

    }
}
