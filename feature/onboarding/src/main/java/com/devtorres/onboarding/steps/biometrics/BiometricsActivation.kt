package com.devtorres.onboarding.steps.biometrics

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CenterFocusWeak
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.devtorres.ui.components.cards.CustomOutlinedCard

@Composable
internal fun BiometricsActivation(
    modifier: Modifier = Modifier,
    biometricsEnabled: Boolean,
    onBiometricsChange: (Boolean) -> Unit
) {
    val animatedBackgroundColor by animateColorAsState(
        targetValue = if(biometricsEnabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceContainerHighest,
        label = "biometricIconBackgroundColor"
    )

    val animatedContentColor by animateColorAsState(
        targetValue = if(biometricsEnabled) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
        label = "biometricIconContentColor"
    )

    val animatedIcon by remember(biometricsEnabled) {
        derivedStateOf {
            if(biometricsEnabled) Icons.Default.CenterFocusWeak else Icons.Default.Fingerprint
        }
    }

    val infiniteTransition = rememberInfiniteTransition(label = "biometricPulse")
    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1600, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "progress"
    )

    CustomOutlinedCard(
        modifier = modifier,
        border = BorderStroke(
            width = 1.5.dp,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .drawBehind {
                        val baseRadius = size.minDimension / 2f

                        drawCircle(
                            color = animatedBackgroundColor,
                            radius = baseRadius * (1f + progress * 0.5f),
                            alpha = 1f - progress
                        )
                    }
                    .clip(CircleShape)
                    .background(animatedBackgroundColor),
                contentAlignment = Alignment.Center
            ) {
                AnimatedContent(
                    targetState = animatedIcon
                ) {
                    Icon(
                        imageVector = it,
                        tint = animatedContentColor,
                        contentDescription = null,
                        modifier = Modifier.size(60.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.size(24.dp))

            AnimatedContent(
                targetState = biometricsEnabled
            ) {
                Text(
                    text = if(it) "El acceso biométrico está activado. Usarás tu huella o rostro para entrar." else "Sin biometría cualquier persona puede ver tu información.",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .alpha(0.5f)
                        .padding(horizontal = 16.dp)
                )
            }

            Spacer(modifier = Modifier.size(8.dp))

            Switch(
                checked = biometricsEnabled,
                onCheckedChange = onBiometricsChange
            )
        }
    }
}