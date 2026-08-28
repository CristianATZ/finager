package com.devtorres.onboarding.saving

import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.devtorres.onboarding.R
import com.devtorres.ui.components.loading.DotsLoading
import com.devtorres.ui.theme.onGreen

@Composable
internal fun SavingOverlayContent(
    username: String,
    containerColor: Color,
    contentColor: Color,
    homeButtonEnabled: Boolean,
    onNavigateHome: () -> Unit
) {
    BackHandler {}

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .windowInsetsPadding(WindowInsets.statusBars)
            .windowInsetsPadding(WindowInsets.navigationBars)
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = stringResource(R.string.onboarding_saving_title),
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
            color = contentColor
        )

        Text(
            text = stringResource(R.string.onboarding_saving_subtitle, username),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = contentColor.copy(alpha = 0.75f),
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.size(16.dp))

        DotsLoading(
            dotsColors = onGreen
        )

        Spacer(modifier = Modifier.weight(2f))

        Button(
            onClick = onNavigateHome,
            colors = ButtonDefaults.buttonColors(
                containerColor = contentColor,
                contentColor = containerColor
            ),
            enabled = homeButtonEnabled,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier
                .animateContentSize()
                .height(48.dp)
                .fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.onboarding_saving_button_go_home))
        }
    }
}
