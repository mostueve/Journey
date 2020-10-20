package com.motek.journey

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels

class JourneyFragment : Fragment() {
    // TODO
    /**
     * layout:
     *  - distance
     *  - warpfactor
     *  - speed
     *  - normal or complicated wf calc
     *  - known destinations Spinner
     *      + Sirius
     *      + Betelgeuze
     *      + Arcturus
     *      + Polaris
     */

    private val viewModel by viewModels<JourneyFragmentViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_journey, container, false)
    }

}