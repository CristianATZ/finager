package com.devtorres.onboarding.steps.summary

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.devtorres.onboarding.state.OnboardingState
import com.devtorres.onboarding.R
import com.devtorres.ui.components.cards.CustomOutlinedCard
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.DiceSix

@Composable
internal fun SummaryScreen(
    modifier: Modifier,
    onboardingState: OnboardingState
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = FontAwesomeIcons.Solid.DiceSix,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    contentDescription = null,
                    modifier = Modifier.size(60.dp)
                )
            }

            Spacer(modifier = Modifier.size(16.dp))

            Text(
                text = stringResource(R.string.onboarding_summary_title, onboardingState.username),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(0.75f)
            )

            Text(
                text = stringResource(R.string.onboarding_summary_subtitle),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                modifier = Modifier.padding(16.dp)
            )

            Spacer(modifier = Modifier.size(32.dp))

            CustomOutlinedCard(
                modifier = Modifier.fillMaxWidth(),
                border = BorderStroke(
                    width = 1.5.dp,
                    color = MaterialTheme.colorScheme.outlineVariant
                )
            ) {
                Column {
                    onboardingState.toPairList().forEach { (titleStringRes, value) ->
                        val mValue = when (value) {
                            is String? -> value ?: ""
                            else -> stringResource(value as Int)
                        }

                        SummaryRow(
                            labelRes = titleStringRes,
                            value = mValue
                        )
                    }
                }
            }
        }
    }
}
