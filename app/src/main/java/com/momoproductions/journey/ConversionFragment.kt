package com.momoproductions.journey

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.widget.doAfterTextChanged
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

    private var currentSelectedUnit: String = "Parsec"
    private var currentEnteredAmount: Double = 0.0


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
        val view =  inflater.inflate(R.layout.fragment_conversion, container, false)
        buildSpinner(view)

        return view
    }

    // add the conversion input field Listener here
    // since the element has to be fully loaded!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        input_field_conversion.doAfterTextChanged {
            var amount_as_string : String? = input_field_conversion?.text.toString()
            if (amount_as_string == "" || amount_as_string == null) {
                amount_as_string = "1.0"
            }

            val amount_as_double : Double = amount_as_string.toDouble()
            this.currentEnteredAmount = amount_as_double

            runConverter()
        }
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

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {

        val selectedItem = parent.getItemAtPosition(pos).toString()

        // get input value
        // get selected unit
        // clear input field
        // write all converted units to fields (includes input unit)
        // switch case for selection of input unit

        // input_field_conversion.setText("1.0", TextView.BufferType.EDITABLE)
        // move into function
        var amount_as_string : String? = input_field_conversion.text.toString()
        if (amount_as_string == "" || amount_as_string == null) {
            amount_as_string = "1.0"
        }
        val amount_as_double : Double = amount_as_string.toDouble()

        this.currentEnteredAmount = amount_as_double
        this.currentSelectedUnit = selectedItem

        runConverter()
        //runConverter(selectedItem, amount_as_double)
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }

    fun runConverter() {
        val inputUnit = unitDictionary.get(this.currentSelectedUnit)
        for (dictEntry in unitDictionary) {
            val currentItem = unitDictionary.get(dictEntry.key)
            if (inputUnit != null && currentItem != null) {
                val converter = DistanceConverter(inputUnit, currentItem)
                val convertedValue = this.currentEnteredAmount.let { converter.convert(it) }
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

    fun runConverterOld(selectedItem : String, amount : Double?) {

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