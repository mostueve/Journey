package com.motek.journey


interface IDistanceConversion {
    // Strategy (= Compositor)
    fun fromMeter(n : Double) : Double
    fun toMeter(n : Double) : Double
}

abstract class Converter {
    abstract fun convert(n : Double) : Double
}

class DistanceConverter constructor(
    converterInput : IDistanceConversion,
    converterOutput : IDistanceConversion) : Converter() {

    // Context (= Composition)
    private var converterInput : IDistanceConversion
    private var converterOutput : IDistanceConversion

    init {
        this.converterInput = converterInput
        this.converterOutput = converterOutput
    }

    override fun convert(n: Double): Double {
        return converterOutput.toMeter(converterInput.fromMeter(n))
    }
}

class Parsec : IDistanceConversion {
    // Concrete Strategy
    private val conversionFactor = 3.0857e16

    override fun fromMeter(n : Double): Double {
        return n * conversionFactor
    }

    override fun toMeter(n : Double): Double {
        return n / conversionFactor
    }
}

class Lightyear : IDistanceConversion {
    // Concrete Strategy
    private val conversionFactor = 9.4607304725808e15

    override fun fromMeter(n : Double): Double {
        return n * conversionFactor
    }

    override fun toMeter(n : Double): Double {
        return n / conversionFactor
    }
}

class AstronomicalUnit : IDistanceConversion {
    // Concrete Strategy
    private val conversionFactor = 1.495978707e11

    override fun fromMeter(n : Double): Double {
        return n * conversionFactor
    }

    override fun toMeter(n : Double): Double {
        return n / conversionFactor
    }
}

class Kilometer : IDistanceConversion {
    private val conversionFactor = 1.0e3

    override fun fromMeter(n : Double): Double {
        return n * conversionFactor
    }

    override fun toMeter(n : Double): Double {
        return n / conversionFactor
    }
}

class Meter : IDistanceConversion {
    private val conversionFactor = 1.0

    override fun fromMeter(n : Double): Double {
        return n * conversionFactor
    }

    override fun toMeter(n : Double): Double {
        return n / conversionFactor
    }
}

