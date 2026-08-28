package com.devtorres.ui.components.stepper.segmented.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun SegmentedStep(
    modifier: Modifier = Modifier,
    index: Int,
    currentStep: Int,
) {
    val isReached = index <= currentStep
    val fillFraction by animateFloatAsState(
        targetValue = if (isReached) 1f else 0f,
        label = "stepFill$index"
    )
    val fillColor by animateColorAsState(
        targetValue = if (index == currentStep) {
            MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
        } else {
            MaterialTheme.colorScheme.primary
        },
        label = "stepColor$index"
    )

    Box(
        modifier = modifier
            .height(8.dp)
            .clip(RoundedCornerShape(50))
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(fillFraction)
                .clip(RoundedCornerShape(50))
                .background(fillColor)
        )
    }
}