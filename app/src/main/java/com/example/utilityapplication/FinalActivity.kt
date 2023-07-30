package com.example.utilityapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import com.example.utilityapplication.convertingLogic.Lengthunits
import com.example.utilityapplication.convertingLogic.fromData
import com.example.utilityapplication.convertingLogic.getUnitArray
import com.example.utilityapplication.convertingLogic.unitsMap
import com.example.utilityapplication.databinding.ActivityFinalBinding
import com.example.utilityapplication.databinding.ActivityMainBinding

class FinalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFinalBinding
    val unitType = convertingLogic.UnitType
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_final)

        val arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            getUnitArray(unitType)
        )

        binding.spinnerUnitFrom.adapter = arrayAdapter
        binding.spinnerUnitTo.adapter = arrayAdapter

        binding.spinnerUnitFrom.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedUnit = getUnitArray(unitType)[position]
                    binding.edtUnitFrom.hint = selectedUnit
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

                    val selectedUnit = getUnitArray(unitType)[position]
                    binding.edtUnitTo.hint = selectedUnit
                    Log.d("You clicked UnitTo", "onItemSelected:$selectedUnit")
                }


                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Empty implementation
                }
            }

        binding.btnSubmit.setOnClickListener {
            fromData = binding.edtUnitFrom.text.toString().toDoubleOrNull() ?: 0.0
            if (fromData==0.0) {
                Toast.makeText(this@FinalActivity, "Enter Value for conversion", Toast.LENGTH_SHORT)
                    .show()

            } else {
                val fromUnit = getUnitArray(unitType)[binding.spinnerUnitFrom.selectedItemPosition]
                val toUnit = getUnitArray(unitType)[binding.spinnerUnitTo.selectedItemPosition]
                val result = convertingLogic.resetUnits(fromData, fromUnit, toUnit)
                    .toString() +" "+convertingLogic.unitsMap[toUnit].toString()
                Log.d("You clicked Unit", "fromData:$fromData,toData:$result, fromUnit:$fromUnit, toUnit:$toUnit")
                binding.edtUnitTo.setText(result.toString())
            }
        }

    }


}