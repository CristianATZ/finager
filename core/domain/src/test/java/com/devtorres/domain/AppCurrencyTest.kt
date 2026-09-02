package com.devtorres.domain

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.NumberFormat

class AppCurrencyTest {


    @Test
    fun `MXN format represents the correct amount`() {
        val amount = 1234.56
        val formatted = AppCurrency.MXN.formatCurrency(amount)

        val parseBack = NumberFormat.getCurrencyInstance(AppCurrency.MXN.locale)
            .parse(formatted)
            .toDouble()

        assertEquals(amount, parseBack, 0.0)
    }
}