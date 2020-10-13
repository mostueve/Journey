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
    private val conversionFactor = 3.0857e16

    override fun fromMeter(n : Double): Double {
        return n * conversionFactor
    }

    override fun toMeter(n : Double): Double {
        return n / conversionFactor
    }
}

class Lightyear : DistanceConversion {
    // Concrete Strategy
    private val conversionFactor = 9.4607304725808e15

    override fun fromMeter(n : Double): Double {
        return n * conversionFactor
    }

    override fun toMeter(n : Double): Double {
        return n / conversionFactor
    }
}

class AstronomicalUnit : DistanceConversion {
    // Concrete Strategy
    private val conversionFactor = 1.495978707e11

    override fun fromMeter(n : Double): Double {
        return n * conversionFactor
    }

    override fun toMeter(n : Double): Double {
        return n / conversionFactor
    }
}

class Kilometer : DistanceConversion {
    private val conversionFactor = 1.0e3

    override fun fromMeter(n : Double): Double {
        return n * conversionFactor
    }

    override fun toMeter(n : Double): Double {
        return n / conversionFactor
    }
}

class Meter : DistanceConversion {
    private val conversionFactor = 1.0

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
    private var converterInput : DistanceConversion
    private var converterOutput : DistanceConversion

    init {
        this.converterInput = converterInput
        this.converterOutput = converterOutput
    }

    override fun convert(n: Double): Double {
        return converterOutput.toMeter(converterInput.fromMeter(n))
    }
}