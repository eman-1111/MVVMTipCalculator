package com.wasltec.tipcalculater.viewmodel

import android.app.Application
import android.databinding.BaseObservable
import com.wasltec.tipcalculater.R
import com.wasltec.tipcalculater.model.Calculator
import com.wasltec.tipcalculater.model.TipCalculation


class CalculatorViewModel(val app: Application,val calculator: Calculator = Calculator()) : BaseObservable() {

    var inputCheckAmount = ""
    var inputTipPercentage = ""

    var outputCheckAmount = ""
    var OutputTipAmount = ""
    var OutputGrandDollarAmount = ""


    init {
        updateOutputs(TipCalculation())
    }

    private fun updateOutputs(tc: TipCalculation) {

        outputCheckAmount = app.getString(R.string.dollar_amount ,tc.checkAmount)
        OutputTipAmount = app.getString(R.string.dollar_amount ,tc.tipAmount)
        OutputGrandDollarAmount = app.getString(R.string.dollar_amount ,tc.grandTotal)
    }


    fun calculateTip() {
        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPct = inputTipPercentage.toIntOrNull()

        if (checkAmount != null && tipPct != null) {
            updateOutputs(calculator.calculateTip(checkAmount, tipPct))
            clearInputs()
        }
    }

    fun clearInputs() {
        inputCheckAmount = "0.00"
        inputTipPercentage = "0"
        notifyChange()
    }
}