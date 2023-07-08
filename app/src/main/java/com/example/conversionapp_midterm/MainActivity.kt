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
    }
}