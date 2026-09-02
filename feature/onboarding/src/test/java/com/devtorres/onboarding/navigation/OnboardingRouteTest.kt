package com.devtorres.onboarding.navigation

import org.junit.Assert.assertEquals
import org.junit.Test

class OnboardingRouteTest {

    @Test
    fun `next step from intro is username`() {
        val currentStep = IntroRoute
        val nextStep = currentStep.next()

        assertEquals(UsernameRoute, nextStep)
    }

    @Test
    fun `next step from username is currency`() {
        val currentStep = UsernameRoute
        val nextStep = currentStep.next()

        assertEquals(CurrencyRoute, nextStep)
    }

    @Test
    fun `next step from currency is language`() {
        val currentStep = CurrencyRoute
        val nextStep = currentStep.next()

        assertEquals(LanguageRoute, nextStep)
    }

    @Test
    fun `next step from language is theme`() {
        val currentStep = LanguageRoute
        val nextStep = currentStep.next()

        assertEquals(ThemeRoute, nextStep)
    }

    @Test
    fun `next step from theme is biometrics`() {
        val currentStep = ThemeRoute
        val nextStep = currentStep.next()

        assertEquals(BiometricsRoute, nextStep)
    }

    @Test
    fun `next step from biometrics is summary`() {
        val currentStep = BiometricsRoute
        val nextStep = currentStep.next()

        assertEquals(SummaryRoute, nextStep)
    }

    @Test
    fun `next step from summary is null`() {
        val currentStep = SummaryRoute
        val nextStep = currentStep.next()

        assertEquals(null, nextStep)
    }

    @Test
    fun `walking the full chain from intro visits every step in order`() {
        val visited = generateSequence(IntroRoute as OnboardingRoute?) { it.next() }
            .take(ONBOARDING_STEP_COUNT + 2)
            .toList()

        assertEquals(
            listOf(IntroRoute, UsernameRoute, CurrencyRoute, LanguageRoute, ThemeRoute, BiometricsRoute, SummaryRoute),
            visited
        )
    }

    @Test
    fun `only intro hides topbar and back button`() {
        val step = IntroRoute

        assertEquals(false, step.topBarVisible)
        assertEquals(false, step.backButtonVisible)
    }
}