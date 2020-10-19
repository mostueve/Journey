package com.motek.journey

abstract class ConverterFactory {
    abstract fun createConverter(inputUnitDescriptor: String, outputUnitDescriptor: String) : Converter
}

class DistanceConverterFactory : ConverterFactory() {

    private lateinit var inputUnit : IDistanceConversion
    private lateinit var outputUnit : IDistanceConversion

    override fun createConverter(inputUnitDescriptor: String, outputUnitDescriptor: String) : Converter {
        when (inputUnitDescriptor) {
            "Parsec" -> inputUnit = Parsec()
            "Lightyear" -> inputUnit = Lightyear()
            "AU" -> inputUnit = AstronomicalUnit()
            "Kilometer" -> inputUnit = Kilometer()
            "Meter" -> inputUnit = Meter()
        }
        when (outputUnitDescriptor) {
            "Parsec" -> outputUnit = Parsec()
            "Lightyear" -> outputUnit = Lightyear()
            "AU" -> outputUnit = AstronomicalUnit()
            "Kilometer" -> outputUnit = Kilometer()
            "Meter" -> outputUnit = Meter()
        }

//        inputUnit = makeUnit(inputUnitDescriptor)
//        outputUnit = makeUnit(outputUnitDescriptor)

        return DistanceConverter(inputUnit, outputUnit)
    }


//    private fun makeUnit(unitDescriptor: String) : IDistanceConversion {
//        when (unitDescriptor) {
//            "Parsec" -> return Parsec()
//            "Lightyear" -> return Lightyear()
//            "AU" -> return AstronomicalUnit()
//            "Kilometer" -> return Kilometer()
//            "Meter" -> return Meter()
//        }
//    }
}