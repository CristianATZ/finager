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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.devtorres.domain.AppLanguage
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
            text = "Tu idioma",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.size(4.dp))

        Text(
            text = "Selecciona el idioma en el que quieres usar la aplicación.",
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
                    title = mLanguage.endonym,
                    subtitle = mLanguage.exonym(),
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
