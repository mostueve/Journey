package journey

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_conversion.*


class ConversionFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var meter: Meter
    private lateinit var kilometer : Kilometer
    private lateinit var astronomicalUnit : AstronomicalUnit
    private lateinit var lightyear : Lightyear
    private lateinit var parsec : Parsec
    private lateinit var unitDictionary : HashMap<String, IDistanceConversion>
    private var currentSelectedUnit: String = "Parsec"
    private var currentEnteredAmount: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // move to factory
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_conversion, container, false)
        buildSpinner(view)

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

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {

        val selectedItem = parent.getItemAtPosition(pos).toString()
        var amount_as_string : String? = input_field_conversion.text.toString()
        if (amount_as_string == "" || amount_as_string == null) {
            input_field_conversion.setText("1.0", TextView.BufferType.EDITABLE)
            amount_as_string = "1.0"
        }
        val amount_as_double : Double = amount_as_string.toDouble()
        this.currentEnteredAmount = amount_as_double
        this.currentSelectedUnit = selectedItem
        runConverter()
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }

    // separate concerns better
    // move Conversion constructors into factory
    // maybe use builder pattern for UI
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
}