package com.example.pokemon.screens.pokemon_detail_screen

import androidx.lifecycle.ViewModel
import com.example.pokemon.data.PokemonDetailResponse
import com.example.pokemon.repository.PokemonMainRepository
import com.example.pokemon.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel@Inject constructor(
    private val repository: PokemonMainRepository
) : ViewModel(){

    suspend fun getPokemonInfo(pokemonName : String) : Resource<PokemonDetailResponse>{
        return repository.getPokemonInfo(pokemonName)
    }
}