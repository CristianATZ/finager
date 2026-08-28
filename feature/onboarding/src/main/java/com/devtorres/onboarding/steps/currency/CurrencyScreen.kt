package com.devtorres.onboarding.steps.currency

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
import com.devtorres.domain.AppCurrency
import com.devtorres.onboarding.R
import com.devtorres.ui.appearance.countryName
import com.devtorres.ui.appearance.displayName
import com.devtorres.ui.components.cards.SelectableCard

@Composable
internal fun CurrencyScreen(
    modifier: Modifier = Modifier,
    currency: AppCurrency? = null,
    onCurrencyChange: (AppCurrency) -> Unit = {}
) {
    Column (
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = stringResource(R.string.onboarding_currency_title),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.size(4.dp))

        Text(
            text = stringResource(R.string.onboarding_currency_subtitle),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
        )

        Spacer(modifier = Modifier.size(32.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AppCurrency.entries.forEach { mCurrency ->
                SelectableCard(
                    icon = {
                        Text(
                            text = mCurrency.symbol,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleLarge
                        )
                    },
                    title = mCurrency.code,
                    subtitle = "${stringResource(mCurrency.displayName())} · ${stringResource(mCurrency.countryName())}",
                    selected = currency == mCurrency,
                    focusedColor = MaterialTheme.colorScheme.primary,
                    onFocusedColor = MaterialTheme.colorScheme.onPrimary,
                    onClick = {
                        onCurrencyChange(mCurrency)
                    }
                )
            }
        }
    }
}
