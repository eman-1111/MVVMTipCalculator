package com.wasltec.tipcalculater.viewmodel

import android.app.Application
import android.databinding.BaseObservable
import com.wasltec.tipcalculater.R
import com.wasltec.tipcalculater.model.Calculator
import com.wasltec.tipcalculater.model.TipCalculation


class CalculatorViewModel @JvmOverloads constructor( app: Application,val calculator: Calculator = Calculator()) : ObservableViewModel(app){

    var inputCheckAmount = ""
    var inputTipPercentage = ""

    var outputCheckAmount = ""
    var OutputTipAmount = ""
    var OutputGrandDollarAmount = ""


    init {
        updateOutputs(TipCalculation())
    }

    private fun updateOutputs(tc: TipCalculation) {

        outputCheckAmount = getApplication<Application>().getString(R.string.dollar_amount ,tc.checkAmount)
        OutputTipAmount = getApplication<Application>().getString(R.string.dollar_amount ,tc.tipAmount)
        OutputGrandDollarAmount = getApplication<Application>().getString(R.string.dollar_amount ,tc.grandTotal)
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