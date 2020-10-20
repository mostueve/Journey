package com.motek.journey

abstract class ConverterFactory {
    abstract fun createConverter(inputUnitDescriptor: String, outputUnitDescriptor: String) : Converter
}

class DistanceUnitConverterFactory : ConverterFactory() {

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
        return DistanceConverter(inputUnit, outputUnit)
    }
}

class AlternateDistanceUnitConverterFactory {
    private lateinit var GeneratedUnit : IDistanceConversion

    fun createUnit(unitDescriptor : String) : IDistanceConversion {
        when (unitDescriptor) {
            "Parsec" -> GeneratedUnit = Parsec()
            "Lightyear" -> GeneratedUnit = Lightyear()
            "AU" -> GeneratedUnit = AstronomicalUnit()
            "Kilometer" -> GeneratedUnit = Kilometer()
            "Meter" -> GeneratedUnit = Meter()
        }
        return GeneratedUnit
    }
}

class VelocityUnitConverterFactory : ConverterFactory() {
    override fun createConverter(
        inputUnitDescriptor: String,
        outputUnitDescriptor: String
    ): Converter {
        TODO("Not yet implemented")
    }

}