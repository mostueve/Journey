package com.momoproductions.journey

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_conversion.*


class ConversionFragment : Fragment(), AdapterView.OnItemSelectedListener {
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

    private lateinit var meter: Meter
    private lateinit var kilometer : Kilometer
    private lateinit var astronomicalUnit : AstronomicalUnit
    private lateinit var lightyear : Lightyear
    private lateinit var parsec : Parsec

    private lateinit var unitDictionary : HashMap<String, DistanceConversion>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        meter = Meter()
        parsec = Parsec()
        lightyear = Lightyear()
        kilometer = Kilometer()
        astronomicalUnit = AstronomicalUnit()

        unitDictionary = HashMap()
        unitDictionary.put("Parsec", parsec)
        unitDictionary.put("Lightyear", lightyear)
        unitDictionary.put("Kilometer", kilometer)
        unitDictionary.put("AU", astronomicalUnit)
        unitDictionary.put("Meter", meter)




    }

//    inline fun <reified T,  reified E> checkType(toCheck: T, substituteType: E) : Boolean {
//        return toCheck !is E
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_conversion, container, false)
        buildSpinner(view)

        //input_field_conversion.setText("1.0", TextView.BufferType.EDITABLE)

        return view
    }


    fun buildSpinner(view: View) {
        val spinner: Spinner = view.findViewById(R.id.distance_measures_spinner)
// Create an ArrayAdapter using the string array and a default spinner layout

        ArrayAdapter.createFromResource(
            requireActivity().baseContext,
            R.array.distance_measures_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = this
    }

    // Problem:
    //  when changing the value and trying to convert to the same
    //  unit as is already selected ...
    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {

        val selectedItem = parent.getItemAtPosition(pos).toString()
        System.out.println("Selected Item: " + selectedItem.toString())

        // get input value
        // get selected unit
        // clear input field
        // write all converted units to fields (includes input unit)
        // switch case for selection of input unit

        // input_field_conversion.setText("1.0", TextView.BufferType.EDITABLE)
        var amount_as_string : String? = input_field_conversion?.text.toString()
        if (amount_as_string == "" || amount_as_string == null) {
            amount_as_string = "1.0"
        }
        System.out.println(amount_as_string)
        //var amount_as_double = 1.0
        var amount_as_double : Double? = amount_as_string?.toDouble()
        runConverter(selectedItem, amount_as_double)
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }

    fun runConverter(selectedItem : String, amount : Double?) {

        var inputUnit = unitDictionary.get(selectedItem)
        for (dictEntry in unitDictionary) {
            var currentItem = unitDictionary.get(dictEntry.key)

            if (inputUnit != null && currentItem != null) {
                var converter = DistanceConverter(inputUnit, currentItem)
                var convertedValue = amount?.let { converter.convert(it) }
                when (dictEntry.key) {
                        "Parsec" -> result_field_parsec?.setText(convertedValue.toString())
                        "Lightyear" -> result_field_lightyear?.setText(convertedValue.toString())
                        "Meter" -> result_field_meter?.setText(convertedValue.toString())
                        "Kilometer" -> result_field_kilometer?.setText(convertedValue.toString())
                        "AU" -> result_field_astronomicalunit?.setText(convertedValue.toString())
                }
            }
        }
    }


}