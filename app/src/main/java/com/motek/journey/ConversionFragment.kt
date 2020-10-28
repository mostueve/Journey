package com.motek.journey

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import kotlinx.android.synthetic.main.fragment_conversion.*


class ConversionFragment : Fragment(), AdapterView.OnItemSelectedListener {

    // private var viewModel : ConversionFragmentViewModel by activityViewModels<ConversionFragmentViewModel>()
    // private var viewModel : ConversionFragmentViewModel  = ViewModelProvider(this).get(ConversionFragmentViewModel::class.java)
    // add ViewModel like this:
    private val viewModel by viewModels<ConversionFragmentViewModel>()

    // setting default to Parsec
    private var currentSelectedSpinnerItem: String = "Parsec"
    private var currentEnteredAmount: Double = 0.0

    // input_field_conversion.setText("1.0", TextView.BufferType.EDITABLE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //var viewModel = ViewModelProvider(this).get<ConversionFragmentViewModel>()
        //var viewModel = ViewModelProvider(this).get(ConversionFragmentViewModel::class.java)
        viewModel.updateValues("helloParsec", 12.7381273)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_conversion, container, false)
        buildSpinner(view)
        updateConversionView()
        return view
    }

    // add the conversion input field Listener here
    // since the element has to be fully loaded
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        input_field_conversion.doAfterTextChanged {
            /**
             * update the distance factor entered and run the converter
             */
            setCurrentEnteredAmount()
            updateConversionView()
        }
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        this.currentSelectedSpinnerItem = parent.getItemAtPosition(pos).toString()
        updateConversionView()
    }

    private fun setCurrentEnteredAmount() {
        var amount_as_string : String? = input_field_conversion.text.toString()
        if (amount_as_string == "" || amount_as_string == null) {
            amount_as_string = "1.0"
        }
        this.currentEnteredAmount = amount_as_string.toDouble()
    }

    private fun updateConversionView() {
        viewModel.updateValues(this.currentSelectedSpinnerItem, this.currentEnteredAmount)
        val convertedValues = viewModel.retrieveCurrentValues()
        displayConvertedValues(convertedValues)
    }

    private fun buildSpinner(view: View) {
        val spinner: Spinner = view.findViewById(R.id.distance_measures_spinner)
        ArrayAdapter.createFromResource(
            requireActivity().baseContext,
            R.array.distance_measures_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = this
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }

    // separate concerns better
    // move Conversion constructors into factory
    // maybe use builder pattern for UI
    fun displayConvertedValues(unitDictionary: HashMap<String, Double>) {
        for (dictEntry in unitDictionary) {
            val currentItem = unitDictionary.get(dictEntry.key)
            when (dictEntry.key) {
                "Parsec" -> result_field_parsec?.setText(currentItem.toString())
                "Lightyear" -> result_field_lightyear?.setText(currentItem.toString())
                "Meter" -> result_field_meter?.setText(currentItem.toString())
                "Kilometer" -> result_field_kilometer?.setText(currentItem.toString())
                "AU" -> result_field_astronomicalunit?.setText(currentItem.toString())
            }
        }
    }
}