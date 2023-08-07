package com.example.utilityapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.utilityapplication.convertingLogic.convertLengthToCentimeters
import com.example.utilityapplication.convertingLogic.convertWeightToKilograms
import com.example.utilityapplication.convertingLogic.getUnitArray
import com.example.utilityapplication.convertingLogic.unitsMap
import com.example.utilityapplication.databinding.FragmentBmicalculatorfragmentBinding

class BmiCalculatorFragment : Fragment() {

    private var unitTypeFrom = "length"
    private var unitTypeTo = "weight"
    private var unitTypeAge = "age"
    private var bmilength = 0.0
    private var bmiweight = 0.0
    private var bmiage: Int = 0
    private var bmigender: Boolean = true

    private lateinit var binding: FragmentBmicalculatorfragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBmicalculatorfragmentBinding.inflate(inflater, container, false)

        val arrayAdapterFrom = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            getUnitArray(unitTypeFrom)
                .toList() // Change Array<String> to List<String>
        )
        val arrayAdapterTo = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            getUnitArray(unitTypeTo)
                .toList() // Change Array<String> to List<String>
        )

        val arrayAdapterAge = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            getUnitArray(unitTypeAge)
        )
        binding.apply {
            spinnerUnitFrom.adapter = arrayAdapterFrom
            spinnerUnitTo.adapter = arrayAdapterTo
            spinnerAge.adapter = arrayAdapterAge

            spinnerUnitFrom.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        val selectedUnit = getUnitArray(unitTypeFrom)[position]
                        edtLength.hint = selectedUnit
                        Log.d("You clickked UnitFrom", "onItemSelected:$selectedUnit ")
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        // Empty implementation
                    }
                }

            spinnerUnitTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedUnit = getUnitArray(unitTypeTo)[position]
                    edtWeight.hint = selectedUnit
                    Log.d("You clicked UnitTo", "onItemSelected:$selectedUnit")
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Empty implementation
                }
            }

            spinnerAge.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedUnit = getUnitArray(unitTypeAge)[position]
                    edtAge.text = selectedUnit
                    Log.d("You clicked UnitAge", "onItemSelected:$selectedUnit")
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Empty implementation
                }
            }

            btnSubmit.setOnClickListener {
                if (binding.edtLength.text.isNullOrBlank() || binding.edtWeight.text.isNullOrBlank() || binding.edtAge.text.isNullOrBlank()) {
                    Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT)
                        .show()

                }
                else {
                    Toast.makeText(requireContext(), "Values filled", Toast.LENGTH_SHORT).show()
                    /*  bmilength = edtLength.text.toString().toDouble()
                      bmiweight = edtWeight.text.toString().toDouble()
                      bmiage = edtAge.text.toString().toInt()*/
                    bmilength = binding.edtLength.text.toString().toDoubleOrNull() ?: 0.0
                    bmiweight = binding.edtWeight.text.toString().toDoubleOrNull() ?: 0.0
                    bmiage = binding.edtAge.text.toString().toIntOrNull() ?: 0
                    // The ?: operator above provides a default value of 0.0 for the doubles and 0 for the integer if parsing fails.

                    // ... (previous code remains the same)
                    myradiogroup.setOnCheckedChangeListener { group, checkedId ->
                        if (radiomale.isChecked) {
                            bmigender = true
                        } else {
                            bmigender = false
                        }

                    }

                    val mto =
                        unitsMap[getUnitArray(unitTypeTo)[spinnerUnitTo.selectedItemPosition]].toString()//weight
                    val mfrom =
                        unitsMap[getUnitArray(unitTypeFrom)[spinnerUnitFrom.selectedItemPosition]].toString()//length
                    val mage = getUnitArray(unitTypeAge)[spinnerAge.selectedItemPosition].toInt()

                    var nfrom = convertLengthToCentimeters(bmilength, mfrom)
                    var nto = convertWeightToKilograms(bmiweight, mto)

                    var nbmi = calculateBMI(nto, nfrom)
                    var nbmiage = categorizeBMIForAge(nbmi, mage, bmigender)
                    bmivalue.text = nbmi.toString()
                    bmicategory.text = nbmiage
                    Log.d(
                        "You clicked Submit",
                        "onItemSelected:$bmilength $bmiweight $bmigender $mto $mfrom $mage $nto $nfrom $nbmi $nbmiage"
                    )
                }

            }





            return root
        }

    }

    private fun calculateBMI(weightKg: Double, heightCm: Double): Double {
        val heightMeters = heightCm / 100
        return weightKg / (heightMeters * heightMeters)
    }

    private fun categorizeBMIForAge(bmi: Double, ageYears: Int, isMale: Boolean): String {
        return if (ageYears < 18) {
            categorizeBMIForChildrenAndAdolescents(bmi, ageYears, isMale)
        } else {
            categorizeBMIForAdults(bmi)
        }
    }

    private fun categorizeBMIForChildrenAndAdolescents(
        bmi: Double,
        ageYears: Int,
        isMale: Boolean
    ): String {
        // Categorization logic for BMI in children and adolescents (age < 18)
        // Implement your own categorization logic based on age, gender, and BMI values
        // For simplicity, let's use a simple categorization below:
        val category: String
        if (bmi < 18.5) {
            category = "Underweight"
        } else if (bmi < 24.9) {
            category = "Normal weight"
        } else if (bmi < 29.9) {
            category = "Overweight"
        } else {
            category = "Obese"
        }
        return category
    }

    private fun categorizeBMIForAdults(bmi: Double): String {
        // Categorization logic for BMI in adults (age >= 18)
        // Implement your own categorization logic based on BMI values
        // For simplicity, let's use a simple categorization below:
        val category: String
        if (bmi < 18.5) {
            category = "Underweight"
        } else if (bmi < 24.9) {
            category = "Normal weight"
        } else if (bmi < 29.9) {
            category = "Overweight"
        } else {
            category = "Obese"
        }
        return category
    }
}
