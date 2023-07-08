package com.example.conversionapp_midterm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.conversionapp_midterm.databinding.ActivityMainBinding
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Define the items for the spinner dropdown
        val items = listOf(
            "km to mi",
            "mi to km",
            "cm to km",
            "in to cm",
            "kg to lb",
            "lb to kg"
        )

        // Create an ArrayAdapter with the items
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)

        // Specify the dropdown layout style
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Set the adapter for the Spinner
        binding.converUnitSpinner.adapter = adapter

        // Set a selection listener for the Spinner
        binding.converUnitSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                updateOutput() // Call the updateOutput function when an item is selected
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing when no item is selected
            }
        }

        // Set a text change listener for the input EditText
        binding.inputEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do nothing before text is changed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                updateOutput() // Call the updateOutput function when the text is changed
            }

            override fun afterTextChanged(s: Editable?) {
                // Do nothing after text is changed
            }
        })
    }

    // Function to update the output based on the current input and spinner selection
    private fun updateOutput() {
        val inputValue = binding.inputEditText.text.toString().toDoubleOrNull()
        val selectedItem = binding.converUnitSpinner.selectedItem.toString()
        val result = makeConversion(inputValue, selectedItem)
        binding.outputTextView.text = result?.toString() ?: "Invalid input"
    }

    // Function to perform the conversion based on the selected conversion unit
    private fun makeConversion(input: Double?, conversionUnit: String): Double? {
        return when (conversionUnit.lowercase()) {
            "km to mi" -> input?.times(0.62)
            "mi to km" -> input?.times(1.61)
            "cm to km" -> input?.div(100000)
            "in to cm" -> input?.times(2.54)
            "kg to lb" -> input?.times(2.2)
            "lb to kg" -> input?.times(0.45)
            else -> null
        }
    }
}
