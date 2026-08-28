package com.devtorres.ui.components.stepper.segmented

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.devtorres.ui.components.stepper.segmented.components.SegmentedStep
import com.devtorres.ui.components.stepper.segmented.components.SegmentedText

@Composable
fun SegmentedStepper(
    modifier: Modifier = Modifier,
    steps: Int,
    currentStep: Int,
    showText: Boolean = false
) {
    Column(
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(steps) { index ->
                SegmentedStep(
                    modifier = Modifier.weight(1f),
                    index = index,
                    currentStep = currentStep
                )
            }
        }

        if(showText) {
            Spacer(modifier = Modifier.size(16.dp))

            SegmentedText(currentStep = currentStep, steps = steps)
        }
    }
}