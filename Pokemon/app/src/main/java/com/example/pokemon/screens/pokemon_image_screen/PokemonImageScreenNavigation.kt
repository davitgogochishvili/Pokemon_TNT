package com.example.pokemon.screens.pokemon_image_screen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val pokemonImageScreen = "pokemonImageScreen"
const val imageUrl = "imageUrl"

fun NavController.navigateToPokemonImageScreen(
    navOptions: NavOptions? = null,
    imageUrl : String,
) {
    this.navigate(route = "$pokemonImageScreen/$imageUrl", navOptions)
}

fun NavGraphBuilder.pokemonImage(
    onBackClick : () -> Unit,
) {
    composable(
        route ="$pokemonImageScreen/{$imageUrl}",
        arguments = listOf(
            navArgument(name = imageUrl) { type = NavType.StringType },
        )) { navBackStackEntry->
        val imageUrl =
            navBackStackEntry.arguments?.getString(imageUrl) ?: ""

        PokemonImageScreen(imageUrl) {
            onBackClick()
        }
    }
}