package com.devtorres.onboarding.steps.language

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import com.devtorres.domain.AppLanguage
import com.devtorres.ui.appearance.endonym
import com.devtorres.ui.appearance.endonymText
import com.devtorres.onboarding.R as OnboardingString
import com.devtorres.ui.R as UiString
import com.devtorres.ui.appearance.exonym
import com.devtorres.ui.components.cards.SelectableCard

@Composable
internal fun LanguageScreen(
    modifier: Modifier = Modifier,
    language: AppLanguage? = null,
    onLanguageChange: (AppLanguage) -> Unit
) {
    Column (
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = stringResource(OnboardingString.string.onboarding_language_title),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.size(4.dp))

        Text(
            text = stringResource(OnboardingString.string.onboarding_language_subtitle),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
        )

        Spacer(modifier = Modifier.size(32.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AppLanguage.entries.forEach { mLanguage ->
                SelectableCard(
                    icon = {
                        Text(
                            text = mLanguage.code,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleMedium
                        )
                    },
                    title = mLanguage.endonymText(),
                    subtitle = stringResource(mLanguage.exonym()),
                    selected = language == mLanguage,
                    focusedColor = MaterialTheme.colorScheme.secondary,
                    onFocusedColor = MaterialTheme.colorScheme.onSecondary,
                    onClick = {
                        onLanguageChange(mLanguage)
                    }
                )
            }
        }
    }
}