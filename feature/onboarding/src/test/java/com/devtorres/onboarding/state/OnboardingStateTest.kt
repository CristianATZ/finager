package com.devtorres.onboarding.state

import com.devtorres.domain.AppCurrency
import com.devtorres.domain.AppLanguage
import com.devtorres.domain.AppTheme
import com.devtorres.onboarding.R
import org.junit.Assert
import org.junit.Test

class OnboardingStateTest {

    @Test
    fun `empty username is not valid`() {
        val state = OnboardingState()

        Assert.assertEquals(false, state.isUsernameValid())
    }

    @Test
    fun `username within one character is not valid`() {
        val state = OnboardingState(
            username = "t"
        )

        Assert.assertEquals(false, state.isUsernameValid())
    }

    @Test
    fun `username is valid within two or more characters`() {
        val stateTwoCharacters = OnboardingState(username = "te")
        val stateThreeCharacters = OnboardingState(username = "tes")
        val stateFourCharacters = OnboardingState(username = "test")

        Assert.assertEquals(true, stateTwoCharacters.isUsernameValid())
        Assert.assertEquals(true, stateThreeCharacters.isUsernameValid())
        Assert.assertEquals(true, stateFourCharacters.isUsernameValid())
    }

    @Test
    fun `currency is not valid`() {
        val state = OnboardingState()

        Assert.assertEquals(false, state.isCurrencyValid())
    }

    @Test
    fun `currency is valid`() {
        val state = OnboardingState(currency = AppCurrency.MXN)

        Assert.assertEquals(true, state.isCurrencyValid())
    }

    @Test
    fun `language is not valid`() {
        val state = OnboardingState()

        Assert.assertEquals(false, state.isLanguageValid())
    }

    @Test
    fun `language is valid`() {
        val state = OnboardingState(language = AppLanguage.EN)

        Assert.assertEquals(true, state.isLanguageValid())
    }

    @Test
    fun `theme is not valid`() {
        val state = OnboardingState()

        Assert.assertEquals(false, state.isThemeValid())
    }

    @Test
    fun `theme is valid`() {
        val state = OnboardingState(theme = AppTheme.LIGHT)

        Assert.assertEquals(true, state.isThemeValid())
    }

    @Test
    fun `transform list is correct`() {
        val state = OnboardingState(
            username = "Test",
            currency = AppCurrency.MXN,
            language = AppLanguage.EN,
            theme = AppTheme.LIGHT,
            biometrics = false
        )

        val expected = listOf<Pair<Int, Any?>>(
            R.string.onboarding_summary_username to "Test",
            R.string.onboarding_summary_currency to "MXN ($)",
            R.string.onboarding_summary_language to "English",
            R.string.onboarding_summary_theme to com.devtorres.ui.R.string.theme_light_title,
            R.string.onboarding_summary_biometrics to com.devtorres.ui.R.string.common_disabled
        )

        Assert.assertEquals(expected, state.toPairList())
    }

    @Test
    fun `transform list reflects EUR dark enabled state`() {
        val state = OnboardingState(
            username = "Test",
            currency = AppCurrency.EUR,
            language = AppLanguage.EN,
            theme = AppTheme.DARK,
            biometrics = true
        )

        val expected = listOf<Pair<Int, Any?>>(
            R.string.onboarding_summary_username to "Test",
            R.string.onboarding_summary_currency to "EUR (€)",
            R.string.onboarding_summary_language to "English",
            R.string.onboarding_summary_theme to com.devtorres.ui.R.string.theme_dark_title,
            R.string.onboarding_summary_biometrics to com.devtorres.ui.R.string.common_enabled
        )

        Assert.assertEquals(expected, state.toPairList())
    }

    @Test
    fun `transform list reflects MXN SYSTEM light disabled state`() {
        val state = OnboardingState(
            username = "Test",
            currency = AppCurrency.MXN,
            language = AppLanguage.SYSTEM,
            theme = AppTheme.LIGHT,
            biometrics = false
        )

        val expected = listOf<Pair<Int, Any?>>(
            R.string.onboarding_summary_username to "Test",
            R.string.onboarding_summary_currency to "MXN ($)",
            R.string.onboarding_summary_language to com.devtorres.ui.R.string.language_system_endonym,
            R.string.onboarding_summary_theme to com.devtorres.ui.R.string.theme_light_title,
            R.string.onboarding_summary_biometrics to com.devtorres.ui.R.string.common_disabled
        )

        Assert.assertEquals(expected, state.toPairList())
    }
}