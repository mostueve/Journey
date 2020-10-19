package com.motek.journey

import androidx.lifecycle.ViewModel

class ConversionFragmentViewModel : ViewModel() {

    private var convertedValuesDictionary = HashMap<String, Double>()
    private val distanceConverterFactory = DistanceConverterFactory()
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

//    fun buildConversionDictionary() {
//        val inputUnit = convertedValuesDictionary.get(this.currentSelectedItem)
//        for (dictEntry in convertedValuesDictionary) {
//            val currentItem = convertedValuesDictionary.get(dictEntry.key)
//            if (inputUnit != null && currentItem != null) {
//                val converter = DistanceConverter(inputUnit, currentItem)
//                val convertedValue = this.amountToConvert.let { converter.convert(it) }
//                when (dictEntry.key) {
//                    "Parsec" -> result_field_parsec?.setText(convertedValue.toString())
//                    "Lightyear" -> result_field_lightyear?.setText(convertedValue.toString())
//                    "Meter" -> result_field_meter?.setText(convertedValue.toString())
//                    "Kilometer" -> result_field_kilometer?.setText(convertedValue.toString())
//                    "AU" -> result_field_astronomicalunit?.setText(convertedValue.toString())
//                }
//            }
//        }
//    }

}