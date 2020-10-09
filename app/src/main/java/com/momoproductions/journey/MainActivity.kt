package com.momoproductions.journey

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var parsec = getResources().getString(R.string.parsec)
        System.out.println(parsec)
        //parsec.toBigDecimal()

        // TODO
        /**
         * navigation code:
         *  - navHostFragment
         *  - function + when
         *  - tabbed or bottom nav?
         *  - everything happens in fragments
         */
    }
}