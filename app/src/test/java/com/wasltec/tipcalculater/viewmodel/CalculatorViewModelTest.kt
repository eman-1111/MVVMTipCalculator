package com.wasltec.tipcalculater.viewmodel

import com.wasltec.tipcalculater.model.Calculator
import com.wasltec.tipcalculater.model.TipCalculation
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class CalculatorViewModelTest {

    lateinit var calculatorViewModel : CalculatorViewModel

    @Mock
    lateinit var  mockCalculator : Calculator

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        calculatorViewModel = CalculatorViewModel()

    }

    @Test
    fun testCalculateTip(){
        calculatorViewModel.inputCheckAmount = "10.00"
        calculatorViewModel.inputTipPercentage = "15"

        val stub = TipCalculation(checkAmount = 10.00,tipPact = 15, tipAmount =  1.5, grandTotal = 11.50)
        `when`(mockCalculator.calculateTip(10.00, 15)).thenReturn(stub)
        calculatorViewModel.calculateTip()


        assertEquals(stub, calculatorViewModel.tipCalculation)
    }

    @Test
    fun testCalculateTipBadPercentage(){
        calculatorViewModel.inputCheckAmount = "10.00"
        calculatorViewModel.inputTipPercentage = ""

        calculatorViewModel.calculateTip()


        verify(mockCalculator, never()).calculateTip(anyDouble(), anyInt())
    }


}