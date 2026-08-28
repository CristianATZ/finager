package com.devtorres.onboarding.steps.username

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.devtorres.onboarding.R
import com.devtorres.ui.components.textfield.outlined.CustomOutlinedTextField

@Composable
internal fun UsernameScreen(
    modifier: Modifier = Modifier,
    username: String = "",
    onUsernameChange: (String) -> Unit = {}
) {
    Column (
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = stringResource(R.string.onboarding_username_title),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.size(4.dp))

        Text(
            text = stringResource(R.string.onboarding_username_subtitle),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
        )

        Spacer(modifier = Modifier.size(32.dp))

        CustomOutlinedTextField(
            value = username,
            onValueChange = onUsernameChange,
            placeholder = stringResource(R.string.onboarding_username_placeholder),
            supportingText = stringResource(R.string.onboarding_username_field_supporting),
            title = stringResource(R.string.onboarding_username_label),
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = null,
                    modifier = Modifier.alpha(0.5f)
                )
            }
        )
    }
}
