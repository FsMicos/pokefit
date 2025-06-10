package com.michael.trocellier.pokefit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.michael.trocellier.pokefit.ui.theme.PokeFitTheme // Asegúrate que la ruta a tu tema sea correcta

// La clase hereda de ComponentActivity para ser una pantalla
class SeleccionaTuPokeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PokeFitTheme { 
                PokeFitSurveyScreen()
            }
        }
    }
}