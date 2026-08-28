package com.devtorres.onboarding.steps.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.devtorres.domain.AppTheme
import com.devtorres.onboarding.R
import com.devtorres.onboarding.components.theme.ThemeOptionCard
import com.devtorres.ui.appearance.icon
import com.devtorres.ui.appearance.iconBackgroundColor
import com.devtorres.ui.appearance.iconTint
import com.devtorres.ui.appearance.subtitle
import com.devtorres.ui.appearance.subtitleColor
import com.devtorres.ui.appearance.title

@Composable
internal fun ThemeScreen(
    modifier: Modifier = Modifier,
    theme: AppTheme? = null,
    onThemeChange: (AppTheme) -> Unit
) {
    Column (
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = stringResource(R.string.onboarding_theme_title),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.size(4.dp))

        Text(
            text = stringResource(R.string.onboarding_theme_subtitle),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
        )

        Spacer(modifier = Modifier.size(32.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AppTheme.entries.forEach { mTheme ->
                ThemeOptionCard(
                    modifier = Modifier.weight(1f),
                    icon = mTheme.icon(),
                    iconBackgroundColor = mTheme.iconBackgroundColor(),
                    iconTint = mTheme.iconTint(),
                    title = stringResource(mTheme.title()),
                    subtitle = stringResource(mTheme.subtitle()),
                    subtitleColor = mTheme.subtitleColor(),
                    focusedColor = MaterialTheme.colorScheme.tertiary,
                    selected = theme == mTheme,
                    onClick = {
                        onThemeChange(mTheme)
                    }
                )
            }
        }
    }
}
