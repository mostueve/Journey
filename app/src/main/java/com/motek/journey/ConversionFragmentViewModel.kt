package com.motek.journey

import androidx.lifecycle.ViewModel

class ConversionFragmentViewModel : ViewModel() {

    // access resources -> only works from correct context
    // var parsec = getResources().getString(R.string.parsec)

    private var convertedValuesDictionary = HashMap<String, Double>()
    private val distanceConverterFactory = DistanceUnitConverterFactory()
    private var currentSelectedItem : String = ""
    private var amountToConvert : Double = 0.0

    private val listOfUnits = ArrayList<String>()

    init {
        listOfUnits.add("Parsec")
        listOfUnits.add("Lightyear")
        listOfUnits.add("Meter")
        listOfUnits.add("Kilometer")
        listOfUnits.add("AU")
    }

    fun updateValues(currentSelectedItem : String, amountToConvert : Double) {
        this.currentSelectedItem = currentSelectedItem
        this.amountToConvert = amountToConvert
    }

    fun retrieveCurrentValues() : HashMap<String, Double> {
        fillValuesDictionary()
        return this.convertedValuesDictionary
    }

    fun getConvertedValuesDictionary() : HashMap<String, Double> {
        return this.convertedValuesDictionary
    }

    private fun fillValuesDictionary() {
        for (unitIdentifier in listOfUnits) {
            val converter = distanceConverterFactory.createConverter(currentSelectedItem, unitIdentifier)
            convertedValuesDictionary.put(unitIdentifier, converter.convert(amountToConvert))
        }
    }

}