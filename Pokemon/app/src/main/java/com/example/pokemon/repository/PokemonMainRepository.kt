package com.example.pokemon.repository

import com.example.pokemon.api.PokemonApi
import com.example.pokemon.data.PokemonDetailResponse
import com.example.pokemon.data.PokemonListResponse
import com.example.pokemon.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PokemonMainRepository@Inject constructor(
    private val api : PokemonApi
) {
    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonListResponse> {
        val response = try {
            api.getPokemonList(limit, offset)
        } catch(e: Exception) {
            return Resource.Error("რაღაცა ნიტოა")
        }
        return Resource.Success(response)
    }

    suspend fun getPokemonInfo(pokemonName: String): Resource<PokemonDetailResponse> {
        val response = try {
            api.getPokemonInfo(pokemonName)
        } catch(e: Exception) {
            return Resource.Error("რაღაცა ნიტოა")
        }
        return Resource.Success(response)
    }



}