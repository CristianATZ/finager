package com.devtorres.ui.components.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun CustomOutlinedCard(
    modifier: Modifier = Modifier,
    border: BorderStroke = CardDefaults.outlinedCardBorder(),
    colors: CardColors = CardDefaults.outlinedCardColors(
        containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
    ),
    shape: Shape = RoundedCornerShape(20.dp),
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val mModifier = if(onClick != null) {
        modifier
            .clip(shape)
            .clickable(onClick = onClick)
    } else modifier

    OutlinedCard(
        modifier = mModifier,
        shape = shape,
        border = border,
        colors = colors
    ) {
        content.invoke()
    }
}