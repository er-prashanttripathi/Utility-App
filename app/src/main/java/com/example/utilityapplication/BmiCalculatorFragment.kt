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
import com.example.utilityapplication.convertingLogic.fromData
import com.example.utilityapplication.convertingLogic.getUnitArray
import com.example.utilityapplication.databinding.FragmentBmicalculatorfragmentBinding

class BmiCalculatorFragment : Fragment() {
    //    var fromData:Double=0.0
    var unitTypeFrom = "length"
    var unitTypeTo = "weight"
    var unitTypeAge = "age"
    var bmilength = 0.0
    var bmiweight = 0.0
    var bmiage:Int = 0
    var bmigender:Boolean=true
//    val bmi=""
//    val category=""

    var arrayType = ""
    private lateinit var binding: FragmentBmicalculatorfragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBmicalculatorfragmentBinding.inflate(inflater, container, false)
//----------------------------------------------------


        val arrayAdapterFrom = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            getUnitArray(unitTypeFrom)
        )
        val arrayAdapterTo = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            getUnitArray(unitTypeTo)
        )
        val arrayAdapterAge = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            getUnitArray(unitTypeAge)
        )


        //---------------------------------------------------
        binding.spinnerUnitFrom.adapter = arrayAdapterFrom
        binding.spinnerUnitTo.adapter = arrayAdapterTo
        binding.spinnerAge.adapter = arrayAdapterAge


        //---------------------------------------------------
        binding.spinnerUnitFrom.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedUnit = getUnitArray(unitTypeFrom)[position]
                    binding.edtLength.hint = selectedUnit
                    Log.d("You clicked UnitFrom", "onItemSelected:$selectedUnit ")
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Empty implementation
                }
            }

        binding.spinnerUnitTo.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    val selectedUnit = getUnitArray(unitTypeTo)[position]
                    binding.edtWeight.hint = selectedUnit
                    Log.d("You clicked UnitTo", "onItemSelected:$selectedUnit")
                }


                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Empty implementation
                }
            }
        binding.spinnerAge.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    val selectedUnit = getUnitArray(unitTypeAge)[position]
                    binding.edtAge.hint = selectedUnit
                    Log.d("You clicked UnitTo", "onItemSelected:$selectedUnit")
                }


                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Empty implementation
                }
            }
//---------------------------------------------------


        binding.btnSubmit.setOnClickListener {
            bmilength = binding.edtLength.text.toString().toDouble()
            bmiweight = binding.edtWeight.text.toString().toDouble()
            bmiage= binding.edtAge.text.toString().toInt()
//            bmigender=binding.radiomale.

            if (fromData == 0.0) {
                Toast.makeText(requireContext(), "Enter a valid number", Toast.LENGTH_SHORT).show()
            } else {
                 val UnitLength =
                     getUnitArray(unitTypeFrom)[binding.spinnerUnitFrom.selectedItemPosition].toDouble()
                 val UnitWeight = getUnitArray(unitTypeTo)[binding.spinnerUnitTo.selectedItemPosition].toDouble()
                val UnitAge = getUnitArray(unitTypeTo)[binding.spinnerAge.selectedItemPosition].toInt()
//                 val result = convertingLogic.resetUnits(fromData, UnitLength, UnitWeight)
                     .toString()
                 Log.d("You clicked Unit", "fromData:$UnitAge, fromUnit:$UnitLength, toUnit:$UnitWeight")
                binding.apply {

                    val bmi = calculateBMI(UnitWeight, UnitLength)
                    val category = categorizeBMIForAge(bmi, UnitAge.toInt(), bmigender)
                    binding.bmivalue.setText(bmi.toString())
                    binding.bmicategory.setText(category.toString())
                    Log.d("You clicked BMI", "BMI:$bmi, Category:$category,Category:$bmiage, Gender:$bmigender")
                }


            }
        }


        return binding.root
    }

    fun calculateBMI(weightKg: Double, heightCm: Double): Double {
        val heightMeters = heightCm / 100
        return weightKg / (heightMeters * heightMeters)
    }

    fun categorizeBMIForAge(bmi: Double, ageYears: Int, isMale: Boolean): String {
        return if (ageYears < 18) {
            categorizeBMIForChildrenAndAdolescents(bmi, ageYears, isMale)
        } else {
            categorizeBMIForAdults(bmi)
        }
    }

    fun categorizeBMIForAdults(bmi: Double): String {
        return when {
            bmi < 18.5 -> "Underweight"
            bmi >= 18.5 && bmi < 24.9 -> "Normal"
            bmi >= 25 && bmi < 29.9 -> "Overweight"
            else -> "Obesity"
        }
    }

    fun categorizeBMIForChildrenAndAdolescents(
        bmi: Double,
        ageYears: Int,
        isMale: Boolean
    ): String {
        // Use age-specific data (BMI percentiles) to categorize BMI for children and adolescents
        // You would need to replace these placeholder values with age and sex-specific data.
        val bmiPercentile = getBMIPercentileForAge(bmi, ageYears, isMale)

        return when {
            bmiPercentile < 5 -> "Underweight"
            bmiPercentile >= 5 && bmiPercentile < 85 -> "Normal"
            bmiPercentile >= 85 && bmiPercentile < 95 -> "Overweight"
            else -> "Obesity"
        }
    }

    fun getBMIPercentileForAge(bmi: Double, ageYears: Int, isMale: Boolean): Double {
        // Placeholder code, replace this with actual data from BMI percentiles chart
        return 50.0
    }

}
