package com.example.pokemon.screens.pokemon_list_screen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val pokemonListScreen = "pokemonListScreen"

fun NavController.navigateToPokemonList(navOptions: NavOptions? = null) {
    this.navigate(pokemonListScreen, navOptions)
}

fun NavGraphBuilder.pokemonList(
    onImageClick: (String) -> Unit,
    onDetailClick : (String,String) -> Unit
) {
    composable(route = pokemonListScreen) {
        PokemonListScreen(
            onImageClick, onDetailClick
        )
    }
}