package com.example.cookingconvertor

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.cookingconvertor.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    lateinit var unit1 : Spinner
    lateinit var unit2: Spinner
    var selectedUnit1: String = ""
    var selectedUnit2: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val itemList1 = listOf("Kg", "g", "lb")
        val itemList2 = listOf("Kg", "g", "lb")

        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, itemList1)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner1.adapter = adapter


        val adapter2 = ArrayAdapter(this, R.layout.simple_spinner_item, itemList2)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner2.adapter = adapter2





        binding.convert.setOnClickListener {
            if (isInputValid()) {
                val weightEntered = binding.weight.text.toString().toDouble()
                val convertedWeight = convertWeight(weightEntered)
                val result = "$convertedWeight $selectedUnit2"
                binding.answer.text = result.toString()
            } else {
                Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show()
            }
        }

       binding.spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
           override fun onItemSelected(
               parent: AdapterView<*>?,
               view: View?,
               position: Int,
               id: Long
           ) {
               selectedUnit1 = itemList1[position]
           }

           override fun onNothingSelected(parent: AdapterView<*>?) {
               Toast.makeText(this@MainActivity, "Please choose a unit to proceed.", Toast.LENGTH_SHORT).show()
           }

       }

        binding.spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedUnit2 = itemList2[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(this@MainActivity, "Please choose a unit to proceed", Toast.LENGTH_SHORT).show()
            }

        }


    }

    private fun isInputValid(): Boolean {
        val WeightEntered = binding.weight.text.toString()

        return WeightEntered.isNotEmpty()
    }

    private fun convertWeight(weight: Double): Double {
        return when {
            selectedUnit1 == "Kg" && selectedUnit2 == "g" -> weight * 1000
            selectedUnit1 == "Kg" && selectedUnit2 == "lb" -> weight * 2.20462
            selectedUnit1 == "g" && selectedUnit2 == "Kg" -> weight / 1000
            selectedUnit1 == "g" && selectedUnit2 == "lb" -> weight * 0.00220462
            selectedUnit1 == "lb" && selectedUnit2 == "Kg" -> weight * 0.453592
            selectedUnit1 == "lb" && selectedUnit2 == "g" -> weight * 453.592
            else -> weight
        }
    }
}