package com.devtorres.ui.components.stepper.segmented

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

internal val BACKGROUND_COLOR = Color.Gray

internal const val STEPS = 5

@Preview
@Composable
fun SegmentedStepperPreview(

) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BACKGROUND_COLOR),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            repeat(STEPS) {
                SegmentedStepper(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    steps = it + 1,
                    currentStep = it,
                    showText = it % 2 == 0
                )
            }
        }
    }
}