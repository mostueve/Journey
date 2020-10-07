package com.momoproductions.journey

interface DistanceConversion {
    // Strategy (= Compositor)
    fun fromMeter(n : Double) : Double
    fun toMeter(n : Double) : Double
}

abstract class Converter {
    abstract fun convert(n : Double) : Double
}

class Parsec : DistanceConversion {
    // Concrete Strategy
    val conversionFactor = 3.0857e16

    override fun fromMeter(n : Double): Double {
        return n * conversionFactor
    }

    override fun toMeter(n : Double): Double {
        return n / conversionFactor
    }
}

class DistanceConverter constructor(
    converterInput : DistanceConversion,
    converterOutput : DistanceConversion) : Converter() {

    // Context (= Composition)

    private lateinit var converterInput : DistanceConversion
    private lateinit var converterOutput : DistanceConversion

    init {
        this.converterInput = converterInput
        this.converterOutput = converterOutput
    }

    override fun convert(n: Double): Double {
        // check again, good Sir.
        return converterOutput.fromMeter(converterInput.toMeter(n))
    }
}