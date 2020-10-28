package com.motek.journey

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.LinearLayout.generateViewId
import android.widget.Spinner
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import kotlinx.android.synthetic.main.fragment_journey.*


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
     *      + Betelgeuse
     *      + Arcturus
     *      + Polaris
     *      + HR 8799 ~ 129 Ly
     *      + Barnard
     *      + Deneb
     *      + Vega
     *      + Altair
     *      + Wolf 359
     *      + PSR B1620−26 ~ 12400 Ly
     */

    private val viewModel by viewModels<JourneyFragmentViewModel>()

    private lateinit var linearLayout : LinearLayout

    fun testResource() {
        // get the Context to access XML resources
        var context : Context = this.requireContext()
        //var x = getContext()
        println(context)
        println(requireActivity().baseContext)

        println(context.getResources().getString(R.string.parsec))

    }

    fun createLocationSpinner(view: View) {
        val spinner: Spinner = view.findViewById(R.id.location_names_spinner)
        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.star_names_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        linearLayout = LinearLayout(this.requireContext())
        linearLayout.setOrientation(LinearLayout.VERTICAL);
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_journey, container, false)
        //testResource()
        createLocationSpinner(view)

        return view
    }

}