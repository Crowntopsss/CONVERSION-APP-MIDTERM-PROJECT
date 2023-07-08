package com.example.conversionapp_midterm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.conversionapp_midterm.databinding.ActivityMainBinding

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

        // Set a click listener for the Calculate button
        binding.calculateButton.setOnClickListener {
            // Get the input value from the EditText
            val inputValue = binding.inputEditText.text.toString().toDoubleOrNull()

            // Perform the conversion based on the selected item in the Spinner
            val selectedItem = binding.converUnitSpinner.selectedItem.toString()
            val result = makeConversion(inputValue, selectedItem)

            // Update the output TextView with the result
            binding.outputTextView.text = result?.toString() ?: "Invalid input"
        }
    }

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
