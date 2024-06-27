package com.example.pokemon.screens.pokemon_detail_screen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.pokemon.screens.pokemon_image_screen.imageUrl

const val pokemonDetailScreen = "pokemonDetailScreen"
const val pokemonName = "pokemonName"
const val pokemonImage = "pokemonImg"

fun NavController.navigateToPokemonDetailScreen(
    navOptions: NavOptions? = null,
    pokemonName : String,
    pokemonImage : String
) {
    this.navigate(route = "$pokemonDetailScreen/$pokemonName/$pokemonImage", navOptions)
}

fun NavGraphBuilder.pokemonDetail(
    onBackClick : () -> Unit,
) {
    composable(
        route = "$pokemonDetailScreen/{$pokemonName}/{$pokemonImage}",
        arguments = listOf(
            navArgument(name = pokemonName) {type = NavType.StringType},
            navArgument(name = pokemonImage) {type = NavType.StringType}

        )
        ) {navBackStackEntry->
        val pokemonName =
            navBackStackEntry.arguments?.getString(pokemonName) ?: ""
        val pokemonImage =
            navBackStackEntry.arguments?.getString(pokemonImage) ?: ""

        PokemonDetailScreen (pokemonName = pokemonName , imageUrl = pokemonImage , onBackClick = onBackClick)
    }
}