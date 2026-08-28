package com.devtorres.ui.components.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

internal val BACKGROUND_COLOR = Color.Gray

@Preview
@Composable
internal fun DostLoadingPreview(

) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BACKGROUND_COLOR),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            repeat(5) {
                DotsLoading(
                    modifier = Modifier,
                    dotsCount = it + 1,
                    dotsColors = Color.White
                )
            }
        }
    }
}