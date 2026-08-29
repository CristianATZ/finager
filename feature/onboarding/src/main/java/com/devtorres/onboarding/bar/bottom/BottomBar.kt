package com.devtorres.onboarding.bar.bottom

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.devtorres.onboarding.OnboardingState
import com.devtorres.onboarding.navigation.CurrencyRoute
import com.devtorres.onboarding.navigation.LanguageRoute
import com.devtorres.onboarding.navigation.IntroRoute
import com.devtorres.onboarding.navigation.OnboardingRoute
import com.devtorres.onboarding.navigation.SummaryRoute
import com.devtorres.onboarding.navigation.ThemeRoute
import com.devtorres.onboarding.navigation.UsernameRoute
import com.devtorres.ui.R

@Composable
internal fun BottomBar(
    modifier: Modifier = Modifier,
    step: OnboardingRoute?,
    onboardingState: OnboardingState,
    onBack: () -> Unit,
    onNext: () -> Unit,
    onFinish: () -> Unit
) {
    if(step == null) return

    val buttonText by remember(step) {
        derivedStateOf {
            when (step) {
                IntroRoute -> R.string.common_start
                SummaryRoute -> R.string.common_finish
                else -> R.string.common_next
            }
        }
    }

    val iconButton by remember(step) {
        derivedStateOf {
            when (step) {
                IntroRoute -> null
                SummaryRoute -> Icons.Filled.Done
                else -> Icons.AutoMirrored.Filled.ArrowForward
            }
        }
    }

    val buttonEnabled by remember(step, onboardingState) {
        derivedStateOf {
            when (step) {
                UsernameRoute -> onboardingState.isUsernameValid()
                CurrencyRoute -> onboardingState.isCurrencyValid()
                LanguageRoute -> onboardingState.isLanguageValid()
                ThemeRoute -> onboardingState.isThemeValid()
                else -> true
            }
        }
    }

    AnimatedVisibility(
        visible = step.bottomBarVisible,
        enter = slideInVertically { it } + fadeIn(),
        exit = slideOutVertically { it } + fadeOut(),
    ) {
        Column(
            modifier = modifier
        ) {
            HorizontalDivider(
                color = MaterialTheme.colorScheme.outlineVariant
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .windowInsetsPadding(WindowInsets.navigationBars)
                    .padding(16.dp)
            ) {
                // Back button
                AnimatedVisibility(
                    visible = step.backButtonVisible,
                    enter = fadeIn() + scaleIn() + expandHorizontally(),
                    exit = fadeOut() + scaleOut() + shrinkHorizontally()
                ) {
                    OutlinedIconButton (
                        onClick = onBack,
                        shape = MaterialTheme.shapes.large,
                        border = BorderStroke(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.outlineVariant
                        ),
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(48.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }

                Button(
                    onClick = if (step == SummaryRoute) onFinish else onNext,
                    shape = MaterialTheme.shapes.large,
                    enabled = buttonEnabled,
                    modifier = Modifier
                        .weight(1f)
                        .animateContentSize()
                        .height(48.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        AnimatedContent(
                            targetState = buttonText,
                            label = "nextButtonLabel"
                        ) { text ->
                            Text(
                                text = stringResource(buttonText),
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        AnimatedContent(
                            targetState = iconButton,
                            label = "nextButtonIcon"
                        ) { icon ->
                            icon?.let {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = null,
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
