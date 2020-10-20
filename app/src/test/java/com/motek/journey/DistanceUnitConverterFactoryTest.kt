package com.motek.journey

import org.junit.Test

import org.junit.Assert.*

class DistanceUnitConverterFactoryTest {

    @Test
    fun createConverter() {
        var converterFactory = DistanceUnitConverterFactory()
        var distanceConverter = converterFactory.createConverter("Parsec", "Meter")
        assertTrue(distanceConverter is DistanceConverter)
    }
}