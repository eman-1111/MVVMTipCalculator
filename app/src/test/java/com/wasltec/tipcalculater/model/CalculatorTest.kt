package com.wasltec.tipcalculater.model

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CalculatorTest {
    lateinit var calculator : Calculator

    @Before
    fun setUp(){
        calculator = Calculator()
    }

    @Test
    fun testCalculatorTip(){

        val baseTc = TipCalculation(checkAmount = 10.00)

        val testVal = listOf(baseTc.copy(tipPact = 25, tipAmount = 2.5, grandTotal = 12.50),
                baseTc.copy(tipPact = 15, tipAmount = 1.5, grandTotal = 11.50),
                baseTc.copy(tipPact = 18, tipAmount = 1.8, grandTotal = 11.80)
        )

        testVal.forEach{
            assertEquals(it, calculator.calculateTip(it.checkAmount,it.tipPact))
        }


    }
}