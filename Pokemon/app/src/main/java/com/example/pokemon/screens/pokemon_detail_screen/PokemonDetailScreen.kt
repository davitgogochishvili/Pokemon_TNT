package com.example.pokemon.screens.pokemon_detail_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pokemon.data.PokemonDetailResponse
import com.example.pokemon.data.Stat
import com.example.pokemon.screens.pokemon_image_screen.decodeUrl
import com.example.pokemon.screens.pokemon_list_screen.LoadImageFromUrl
import com.example.pokemon.util.Resource
import timber.log.Timber
import java.util.Locale

@Composable
fun PokemonDetailScreen(
    onBackClick : () -> Unit,
    pokemonName : String,
    imageUrl : String,
    viewModel: PokemonDetailViewModel = hiltViewModel()
){
    Timber.tag("POKEMONNAME").i("$pokemonName")
    val pokemonInfo = produceState<Resource<PokemonDetailResponse>>(initialValue = Resource.Loading()) {
        value = viewModel.getPokemonInfo(pokemonName.toLowerCase(Locale.ROOT))
    }.value

    val imgUrl = decodeUrl(imageUrl)
    Column {
        LoadImageFromUrl(url = imgUrl!!)
        Text(text = "Name from previous page is $pokemonName , \n name from api response is ${pokemonInfo.data?.name}")
        DetailRow(name = "Exp", value = pokemonInfo.data?.base_experience.toString())
        DetailRow(name = "Weight", value = pokemonInfo.data?.weight.toString())
        DetailRow(name = "Height", value = pokemonInfo.data?.height.toString())
        DetailRow(name = "Base Stat", value = pokemonInfo.data?.stats?.get(0)?.base_stat.toString())
        DetailRow(name = "Specie Name", value = pokemonInfo.data?.species?.name)
    }

}

@Composable
fun DetailRow(name : String? , value : String?){
    Row(modifier = Modifier.padding(10.dp)) {
        Text(text = name ?: "that's empty bro")
        Spacer(modifier = Modifier.weight(1f))
        Text(text = value ?: "that's also empty bro")
    }
}
