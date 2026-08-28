package com.devtorres.ui.components.cards.blurred

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.layer.GraphicsLayer
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@Composable
fun BlurredCard(
    modifier: Modifier = Modifier,
    contentLayer: GraphicsLayer,
    contentSize: IntSize,
    content: @Composable () -> Unit
) {
    val blurLayer = rememberGraphicsLayer()
    var barOffset by remember { mutableStateOf(Offset.Zero) }

    Box(
        modifier
            .onGloballyPositioned { coords ->
                barOffset = coords.positionInParent()
            }
            .height(56.dp)
            .clip(RoundedCornerShape(24.dp))
            .border(
                width = 1.dp,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.6f),
                        Color.White.copy(alpha = 0.1f)
                    )
                ),
                shape = RoundedCornerShape(24.dp)
            )
            .drawWithContent {
                blurLayer.renderEffect = BlurEffect(24f, 24f, TileMode.Decal)
                blurLayer.record(size = contentSize) {
                    drawLayer(contentLayer)
                }
                translate(
                    left = -barOffset.x,
                    top = -barOffset.y
                ) {
                    drawLayer(blurLayer)
                }
                drawContent()
            }
    ) {
        content.invoke()
    }
}