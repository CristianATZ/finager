package com.devtorres.domain

import com.devtorres.domain.AppLanguage.Companion.resolveLocale
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Locale

class AppLanguageTest {

    @Test
    fun `ES resolves to Spanish Spain locale`() {
        val result = AppLanguage.ES.resolveLocale()

        assertEquals(Locale("es", "ES"), result)
    }

    @Test
    fun `EN resolves to English locale`() {
        val result = AppLanguage.EN.resolveLocale()

        assertEquals(Locale.ENGLISH, result)
    }

    @Test
    fun `FR resolves to French locale`() {
        val result = AppLanguage.FR.resolveLocale()

        assertEquals(Locale.FRENCH, result)
    }

    @Test
    fun `SYSTEM resolves to the JVM default locale`() {
        val result = AppLanguage.SYSTEM.resolveLocale()

        assertEquals(Locale.getDefault(), result)
    }
}
