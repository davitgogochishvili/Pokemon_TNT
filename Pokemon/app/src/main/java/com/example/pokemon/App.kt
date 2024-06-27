package com.example.pokemon

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.pokemon.navigation.AppNavHost
import com.example.pokemon.ui.theme.PokemonTheme

@Composable
fun App(
    appState: AppState = rememberAppState(

    )
) {
    PokemonTheme {
        Scaffold (
            topBar = {
                Surface {
                    var showWarning by rememberSaveable { mutableStateOf(false) }
                    TopBar(
                        screenTitle = appState.currentDestination,
                        onBackClick = appState::onBackClick
                    )
                }
            },
            content = { padding ->
                Surface {
                    AppNavHost(
                        navController = appState.navController,
                        modifier = Modifier
                            .padding(padding)
                            .fillMaxHeight()
                            .fillMaxWidth()
                    )
                }
            }

        )
    }

}