package com.example.pokemon.screens.pokemon_list_screen

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.data.PokemonUiModel
import com.example.pokemon.repository.PokemonMainRepository
import com.example.pokemon.util.Constants.PAGE_SIZE
import com.example.pokemon.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel@Inject constructor(
   private val pokemonMainRepository: PokemonMainRepository
) : ViewModel() {
    private var curPage = 0

    var pokemonList = mutableStateOf<List<PokemonUiModel>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    init {
        loadPokemonList()
    }
    fun loadPokemonList(){
        viewModelScope.launch {
            val result = pokemonMainRepository.getPokemonList(PAGE_SIZE,curPage * PAGE_SIZE)
            when(result) {
                is Resource.Success -> {
                    endReached.value = curPage * PAGE_SIZE >= result.data!!.count
                    val pokedexEntries = result.data.results.mapIndexed { index, entry ->
                        val number = if(entry.url.endsWith("/")) {
                            entry.url.dropLast(1).takeLastWhile { it.isDigit() }
                        } else {
                            entry.url.takeLastWhile { it.isDigit() }
                        }
                        val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
                        PokemonUiModel(entry.name.capitalize(Locale.ROOT), url, number.toInt())
                    }
                    curPage++

                    pokemonList.value += pokedexEntries
                }
                is Resource.Error -> {
                }

                is Resource.Loading -> {

                }
            }
        }
    }

}