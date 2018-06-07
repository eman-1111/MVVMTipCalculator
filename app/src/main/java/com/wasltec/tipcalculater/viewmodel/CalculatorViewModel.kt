package com.wasltec.tipcalculater.viewmodel

import com.wasltec.tipcalculater.model.Calculator
import com.wasltec.tipcalculater.model.TipCalculation


class CalculatorViewModel(val calculator : Calculator = Calculator() ) {

    var inputCheckAmount = ""
    var inputTipPercentage = ""

    var tipCalculation  = TipCalculation()

    fun calculateTip(){
        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPct = inputTipPercentage.toIntOrNull()

        if(checkAmount != null && tipPct != null){
            tipCalculation = calculator.calculateTip(checkAmount, tipPct)

        }
    }
}