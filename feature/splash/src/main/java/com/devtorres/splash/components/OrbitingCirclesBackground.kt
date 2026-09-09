package com.devtorres.splash.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

@Composable
internal fun OrbitingCirclesBackground(
    modifier: Modifier = Modifier,
    contentPadding: Dp = 0.dp,
    backgroundColor: Color,
    circleColor: Color,
    content: @Composable BoxScope.() -> Unit
) {
    val innerOrbitTransition = rememberInfiniteTransition(label = "innerOrbit")
    val innerOrbitAngle by innerOrbitTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 10000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "innerOrbitAngle"
    )

    val outerOrbitTransition = rememberInfiniteTransition(label = "outerOrbit")
    val outerOrbitAngle by outerOrbitTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 15000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "outerOrbitAngle"
    )

    Box(
        modifier = modifier
            .drawWithContent {
                drawRect(color = backgroundColor)

                val orbitRadius = 25.dp.toPx()
                val orbitCenter = Offset(size.width * 0.5f, size.height * 0.5f)

                val innerAngleRad = Math.toRadians(innerOrbitAngle.toDouble())
                drawCircle(
                    color = circleColor.copy(alpha = 0.25f),
                    radius = 200.dp.toPx(),
                    center = Offset(
                        x = orbitCenter.x + (cos(innerAngleRad) * orbitRadius).toFloat(),
                        y = orbitCenter.y + (sin(innerAngleRad) * orbitRadius).toFloat()
                    ),
                    style = Stroke(width = 0.5.dp.toPx())
                )

                val outerAngleRad = Math.toRadians(outerOrbitAngle.toDouble())
                drawCircle(
                    color = circleColor.copy(alpha = 0.25f),
                    radius = 350.dp.toPx(),
                    center = Offset(
                        x = orbitCenter.x + (cos(outerAngleRad) * orbitRadius).toFloat(),
                        y = orbitCenter.y + (sin(outerAngleRad) * orbitRadius).toFloat()
                    ),
                    style = Stroke(width = 0.5.dp.toPx())
                )

                drawContent()
            }
            .padding(contentPadding)
    ) {
        content()
    }
}