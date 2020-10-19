package com.motek.journey

import org.junit.Test

import org.junit.Assert.*

class DistanceConverterTest {

    private val parsec = Parsec()
    private val lightyear = Lightyear()
    private val au = AstronomicalUnit()
    private val kilometer = Kilometer()
    private val meter = Meter()

    @Test
    fun parsecToParsec() {
        val converter = DistanceConverter(parsec, parsec)
        assertEquals(1.0, converter.convert(1.0), 0.0000001)
    }

    @Test
    fun kilometerToMeter() {
        val converter = DistanceConverter(kilometer, meter)
        assertEquals(1000.0, converter.convert(1.0), 0.0000001)
    }
}