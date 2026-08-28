package com.devtorres.onboarding.steps.biometrics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.devtorres.onboarding.R

@Composable
internal fun BiometricsScreen(
    modifier: Modifier = Modifier,
    biometrics: Boolean,
    onBiometricsChange: (Boolean) -> Unit
) {
    Column (
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.onboarding_biometrics_title),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.size(4.dp))

        Text(
            text = stringResource(R.string.onboarding_biometrics_subtitle),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
        )

        Spacer(modifier = Modifier.size(32.dp))

        BiometricsActivationCard(
            modifier = Modifier.fillMaxWidth(),
            biometricsEnabled = biometrics,
            onBiometricsChange = onBiometricsChange
        )
    }
}
