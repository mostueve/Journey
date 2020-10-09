package com.momoproductions.journey

import android.content.Context
import org.junit.Test

import org.junit.Assert.*

class DistanceConverterTest {

    private lateinit var parsec : Parsec
    private lateinit var converter : DistanceConverter

    @Test
    fun convert() {
        parsec = Parsec()
        //val context = this.context
        //val someString = context?.resources.constants(R.con) //context is nullable so "?" is needed

        converter = DistanceConverter(parsec, parsec)
        //assertSame("1.0 Parsec", 1.0, converter.convert(1.0))
        assertEquals(1.0, converter.convert(1.0), 0.0000001)
    }
}