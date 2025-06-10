package com.michael.trocellier.pokefit

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.michael.trocellier.pokefit.ui.theme.PokeFitTheme

private enum class SurveyStep {
    INITIAL,
    TRAINING_IMPROVEMENT,
    GENERAL_HEALTH
}

@Composable
fun PokeFitSurveyScreen() {
    var currentStep by remember { mutableStateOf(SurveyStep.INITIAL) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.background,
                        MaterialTheme.colorScheme.secondary
                    )
                )
            )
    ) {
        if (currentStep != SurveyStep.INITIAL) {
            TextButton(
                onClick = { currentStep = SurveyStep.INITIAL },
                modifier = Modifier.align(Alignment.TopStart).padding(16.dp)
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.button_back),
                    tint = MaterialTheme.colorScheme.onBackground
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = stringResource(id = R.string.button_back),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 16.sp
                )
            }
        }

        AnimatedContent(
            targetState = currentStep,
            label = "Survey Step Animation",
            transitionSpec = { fadeIn() togetherWith fadeOut() }
        ) { step ->
            when (step) {
                SurveyStep.INITIAL -> InitialStep(
                    onImproveTrainingClick = { currentStep = SurveyStep.TRAINING_IMPROVEMENT },
                    onGeneralHealthClick = { currentStep = SurveyStep.GENERAL_HEALTH }
                )
                SurveyStep.TRAINING_IMPROVEMENT -> TrainingImprovementStep()
                SurveyStep.GENERAL_HEALTH -> GeneralHealthStep()
            }
        }
    }
}

@Composable
private fun InitialStep(onImproveTrainingClick: () -> Unit, onGeneralHealthClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppHeader(headerText = stringResource(id = R.string.pokefit_header))
        QuestionText(text = stringResource(id = R.string.survey_main_question))
        Spacer(Modifier.height(40.dp))
        SurveyButton(text = stringResource(id = R.string.option_exit_sedentary), onClick = onGeneralHealthClick)
        Spacer(Modifier.height(20.dp))
        SurveyButton(text = stringResource(id = R.string.option_improve_training), onClick = onImproveTrainingClick)
    }
}


@Composable
private fun TrainingImprovementStep() {
    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppHeader(headerText = stringResource(id = R.string.pokefit_header))
        QuestionText(text = stringResource(id = R.string.survey_main_question))
        Spacer(Modifier.height(40.dp))
        SurveyButton(text = stringResource(id = R.string.option_velocity), onClick = { /* TODO */ })
        Spacer(Modifier.height(20.dp))
        SurveyButton(text = stringResource(id = R.string.option_strength), onClick = { /* TODO */ })
        Spacer(Modifier.height(20.dp))
        SurveyButton(text = stringResource(id = R.string.option_resistance), onClick = { /* TODO */ })
    }
}

@Composable
private fun GeneralHealthStep() {
    var steps by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppHeader(headerText = stringResource(id = R.string.pokefit_header))
        QuestionText(text = stringResource(id = R.string.survey_steps_question))
        Spacer(Modifier.height(40.dp))
        OutlinedTextField(
            value = steps,
            onValueChange = { steps = it },
            label = { Text(stringResource(id = R.string.label_steps_number)) },
            shape = RoundedCornerShape(16.dp),
            singleLine = true
        )
        Spacer(Modifier.height(20.dp))
        Button(
            onClick = { /* TODO */ },
            shape = RoundedCornerShape(12.dp),
            // El color del botón ahora viene del tema (primary)
            modifier = Modifier.size(width = 80.dp, height = 50.dp)
        ) {
            Text(
                text = stringResource(id = R.string.button_ok),
                // El color del texto es el correspondiente al del botón (onPrimary)
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

// --- Componentes Reutilizables ---

@Composable
private fun AppHeader(headerText: String) {
    Text(
        text = headerText,
        color = MaterialTheme.colorScheme.onBackground,
        fontSize = 48.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        lineHeight = 50.sp
    )
    Spacer(Modifier.height(24.dp))
    Image(
        painter = painterResource(id = R.drawable.pokeball),
        contentDescription = stringResource(id = R.string.pokefit_logo_description),
        modifier = Modifier.size(60.dp)
    )
    Spacer(Modifier.height(48.dp))
}

@Composable
private fun QuestionText(text: String) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.onBackground,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun SurveyButton(text: String, onClick: () -> Unit) {
    // Este botón usará el color `primary` del tema por defecto.
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(50),
        modifier = Modifier.fillMaxWidth().height(56.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                // El color del texto sobre un fondo `primary` es `onPrimary`
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Icon(
                Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokeFitSurveyScreenPreview() {
    PokeFitTheme {
        PokeFitSurveyScreen()
    }
}