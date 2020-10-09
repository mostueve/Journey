package com.momoproductions.journey

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class ConversionFragment : Fragment() {
    // TODO
    /**
     * Layout:
     *  - Units Spinner
     *  - Fields for all units
     *
     * Implementation:
     *  - select one unit in Spinner
     *  - calculate all other unit equivalents
     *  - display in corresponding fields
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_conversion, container, false)
    }

}