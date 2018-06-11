package com.wasltec.tipcalculater.model

import android.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class TipCalculationRepositoryTest {


    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var repository : TipCalculationRepository

    @Before
    fun setUp(){
        repository = TipCalculationRepository()
    }

    @Test
    fun testSaveTip(){
        val tip = TipCalculation(locationName = "Pancake Paradise",
                checkAmount = 100.0, tipPact = 25,
                tipAmount = 25.0, grandTotal = 125.0)

        repository.saveTipCalculation(tip)

        assertEquals(tip, repository.loadTipCalcalation(tip.locationName))
    }

    @Test
    fun testLoadSavedTipCalculations(){
        val tip1 = TipCalculation(locationName = "Pancake Paradise",
                checkAmount = 100.0, tipPact = 25,
                tipAmount = 25.0, grandTotal = 125.0)
        val tip2 = TipCalculation(locationName = "Veggie sensation",
                checkAmount = 100.0, tipPact = 25,
                tipAmount = 25.0, grandTotal = 125.0)

        repository.saveTipCalculation(tip1)
        repository.saveTipCalculation(tip2)

        repository.loadSavedTipCalculations().observeForever{ tipCalculation ->
            assertEquals(2, tipCalculation?.size)

        }


    }
}