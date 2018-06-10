package com.wasltec.tipcalculater.viewmodel

import android.app.Application
import com.wasltec.tipcalculater.R
import com.wasltec.tipcalculater.model.Calculator
import com.wasltec.tipcalculater.model.TipCalculation
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class CalculatorViewModelTest {

    lateinit var calculatorViewModel: CalculatorViewModel

    @Mock
    lateinit var mockCalculator: Calculator

    @Mock
    lateinit var mockApplication: Application

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        stubResource(0.0, "$0.00")
        calculatorViewModel = CalculatorViewModel(mockApplication, mockCalculator)
    }

    private fun stubResource(given: Double, returnStub: String) {
        `when`(mockApplication.getString(R.string.dollar_amount, given)).thenReturn(returnStub)
    }

    @Test
    fun testCalculateTip() {
        calculatorViewModel.inputCheckAmount = "10.00"
        calculatorViewModel.inputTipPercentage = "15"

        val stub = TipCalculation(checkAmount = 10.00, tipPact = 15, tipAmount = 1.5, grandTotal = 11.50)
        `when`(mockCalculator.calculateTip(10.00, 15)).thenReturn(stub)
        calculatorViewModel.calculateTip()

        stubResource(10.00, "$10.00")
        stubResource(1.50, "$1.50")
        stubResource(11.50, "$11.50")


        assertEquals("$10.00", calculatorViewModel.outputCheckAmount)
        assertEquals("$1.50", calculatorViewModel.OutputTipAmount)
        assertEquals("$11.50", calculatorViewModel.OutputGrandDollarAmount)
    }

    @Test
    fun testCalculateTipBadPercentage() {
        calculatorViewModel.inputCheckAmount = "10.00"
        calculatorViewModel.inputTipPercentage = ""

        calculatorViewModel.calculateTip()


        verify(mockCalculator, never()).calculateTip(anyDouble(), anyInt())
    }


}