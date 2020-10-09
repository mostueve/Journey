package com.momoproductions.journey

import kotlin.math.pow

class WarpfactorCalculator {

    // TODO
    /**
     * move to Converter
     */

    val LIGHTSPEED = 299_792_458.0

    fun calculateNormal(warpfactor : Double) : Double  {
        return LIGHTSPEED * warpfactor.pow(10.0/3.0)
    }

    fun calculateIntricate(warpfactor : Double) : Double {
        var exponent = (10.0/3.0) / (1 - (warpfactor/10.0).pow((91.28 / (10.0 - warpfactor).pow(0.27))));
        return LIGHTSPEED * warpfactor.pow(exponent);
    }
}
