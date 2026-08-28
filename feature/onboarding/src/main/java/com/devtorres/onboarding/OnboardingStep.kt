package com.devtorres.onboarding

internal enum class OnboardingStep(
    val step: Int,
    val topBarVisible: Boolean = true,
    val bottomBarVisible: Boolean = true,
    val backButtonVisible: Boolean = true
) {
    START(-1, false, true, false),
    USERNAME(0),
    CURRENCY(1),
    LANGUAGE(2),
    THEME(3),
    BIOMETRICS(4),
    SUMMARY(5);

    fun onboardingSize(): Int = entries.filter { step ->  step.step != -1 }.size

    fun onNext(): OnboardingStep? = entries.getOrNull(this.ordinal + 1)

    fun onBack(): OnboardingStep? = entries.getOrNull(this.ordinal - 1)
}