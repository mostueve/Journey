package com.motek.journey

import android.content.Context
import kotlin.math.pow
import android.R


interface IUnitConversion {}

interface IDistanceConversion : IUnitConversion {
    // Strategy (= Compositor)
    fun fromMeter(n : Double) : Double
    fun toMeter(n : Double) : Double
}

interface IVelocityConversion : IUnitConversion {
    fun fromWarpfactor(n : Double) : Double
    fun toWarpfactor(n : Double) : Double
}

interface ITravelTimeConversion : IUnitConversion {

}

abstract class Converter {
    abstract fun convert(n : Double) : Double
}

class VelocityUnitConverter constructor(
    converterInput : IVelocityConversion,
    converterOutput: IVelocityConversion
) : Converter() {

    override fun convert(n: Double): Double {
        TODO("Not yet implemented")
    }

}

/**
 * val LIGHTSPEED = 299_792_458.0

fun calculateNormal(warpfactor : Double) : Double  {
return LIGHTSPEED * warpfactor.pow(10.0/3.0)
}

fun calculateIntricate(warpfactor : Double) : Double {
var exponent = (10.0/3.0) / (1 - (warpfactor/10.0).pow((91.28 / (10.0 - warpfactor).pow(0.27))));
return LIGHTSPEED * warpfactor.pow(exponent);
}
 */


class BasicWarpfactor : IVelocityConversion {
    override fun fromWarpfactor(n: Double): Double {
        TODO("Not yet implemented")
    }

    override fun toWarpfactor(n: Double): Double {
        TODO("Not yet implemented")
    }
}

class IntricateWarpfactor : IVelocityConversion {
    override fun fromWarpfactor(n: Double): Double {
        TODO("Not yet implemented")
    }

    override fun toWarpfactor(n: Double): Double {
        TODO("Not yet implemented")
    }
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
    //var applicationContext : Context =Context
    //private val conversionFactor = getResources().getString(R.string.parsec)
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

