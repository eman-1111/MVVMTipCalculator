package com.wasltec.tipcalculater.model

import android.arch.lifecycle.LiveData
import java.math.RoundingMode

class Calculator (val repository: TipCalculationRepository = TipCalculationRepository()){

    fun calculateTip(checkAmount : Double, tipPct : Int ) : TipCalculation{
        val tipAmount = (checkAmount  * (tipPct.toDouble() / 100.00))
                .toBigDecimal()
                .setScale(2,RoundingMode.HALF_UP)
                .toDouble()

        val grandTotal = checkAmount + tipAmount
        return TipCalculation(
                checkAmount = checkAmount,
                tipPact = tipPct,
                tipAmount = tipAmount,
                grandTotal = grandTotal
        )
    }


    fun saveTipCalculation(tc : TipCalculation){
        repository.saveTipCalculation(tc)
    }

    fun loadTipCalculationByLocationName(locationName : String) : TipCalculation?{
        return repository.loadTipCalcalation(locationName)
    }

    fun loadSavedTioCalculation(): LiveData<List<TipCalculation>>{
        return repository.loadSavedTipCalculations()
    }
}