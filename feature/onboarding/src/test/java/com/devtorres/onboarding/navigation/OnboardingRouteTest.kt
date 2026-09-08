package com.devtorres.onboarding.navigation

import org.junit.Assert.assertEquals
import org.junit.Test

class OnboardingRouteTest {

    @Test
    fun `next step from intro is username`() {
        val currentStep = OnboardingDestination.IntroRoute
        val nextStep = currentStep.createRoute()

        assertEquals(OnboardingDestination.UsernameRoute, nextStep)
    }

    @Test
    fun `next step from username is currency`() {
        val currentStep = OnboardingDestination.UsernameRoute
        val nextStep = currentStep.createRoute()

        assertEquals(OnboardingDestination.CurrencyRoute, nextStep)
    }

    @Test
    fun `next step from currency is language`() {
        val currentStep = OnboardingDestination.CurrencyRoute
        val nextStep = currentStep.createRoute()

        assertEquals(OnboardingDestination.LanguageRoute, nextStep)
    }

    @Test
    fun `next step from language is theme`() {
        val currentStep = OnboardingDestination.LanguageRoute
        val nextStep = currentStep.createRoute()

        assertEquals(OnboardingDestination.ThemeRoute, nextStep)
    }

    @Test
    fun `next step from theme is biometrics`() {
        val currentStep = OnboardingDestination.ThemeRoute
        val nextStep = currentStep.createRoute()

        assertEquals(OnboardingDestination.BiometricsRoute, nextStep)
    }

    @Test
    fun `next step from biometrics is summary`() {
        val currentStep = OnboardingDestination.BiometricsRoute
        val nextStep = currentStep.createRoute()

        assertEquals(OnboardingDestination.SummaryRoute, nextStep)
    }

    @Test
    fun `next step from summary is null`() {
        val currentStep = OnboardingDestination.SummaryRoute
        val nextStep = currentStep.createRoute()

        assertEquals(null, nextStep)
    }

    @Test
    fun `walking the full chain from intro visits every step in order`() {
        val visited = generateSequence(OnboardingDestination.IntroRoute as OnboardingDestination?) { it.createRoute() }
            .take(OnboardingDestination.totalSteps() + 2)
            .toList()

        assertEquals(
            listOf(
                OnboardingDestination.IntroRoute,
                OnboardingDestination.UsernameRoute,
                OnboardingDestination.CurrencyRoute,
                OnboardingDestination.LanguageRoute,
                OnboardingDestination.ThemeRoute,
                OnboardingDestination.BiometricsRoute,
                OnboardingDestination.SummaryRoute
            ),
            visited
        )
    }
}