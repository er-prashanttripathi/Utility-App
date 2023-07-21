/*
package com.example.utilityapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.utilityapplication.databinding.FragmentBmicalculatorfragmentBinding
import com.example.utilityapplication.databinding.FragmentUnitBinding


class BmiCalculatorFragment : Fragment() {
    private lateinit var binding: FragmentBmicalculatorfragmentBinding

var unit= arrayOf("Meter (m)","Kilometer (km)","Centimeter (cm)","Millimeter (mm)","Inch (in)","Foot (ft)","Yard (yd)","Mile (mi)")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_bmicalculatorfragment, container, false)
        val arrayAdapter=ArrayAdapter<String>(requireContext(),android.R.layout.simple_spinner_dropdown_item,unit)

        binding.spinnerUnitFrom.adapter=arrayAdapter
        binding.spinnerUnitFrom.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                Toast.makeText(requireContext(), "you clicked "+ unit[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        return view
    }


}*//*

package com.example.utilityapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.utilityapplication.databinding.FragmentBmicalculatorfragmentBinding

class BmiCalculatorFragment : Fragment() {
    private lateinit var binding: FragmentBmicalculatorfragmentBinding
    var uFrom =""
    var uTo = ""
    private val Lengthunits = arrayOf(
        "Meter (m)", "Kilometer (km)", "Centimeter (cm)", "Millimeter (mm)",
        "Inch (in)", "Foot (ft)", "Yard (yd)", "Mile (mi)"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBmicalculatorfragmentBinding.inflate(inflater, container, false)
//binding.spinnerUnitTo=convertingLogic.convertUnit()
        val arrayAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            Lengthunits
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
                    val selectedUnit = Lengthunits[position]
                    uFrom=Lengthunits[position]
                    Toast.makeText(
                        requireContext(),
                        "You clicked UnitFrom $selectedUnit",
                        Toast.LENGTH_SHORT
                    ).show()
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
                    val selectedUnit = Lengthunits[position]
                    uTo=Lengthunits[position]
                    Toast.makeText(
                        requireContext(),
                        "You clicked UnitTo $selectedUnit",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.d("You clicked UnitTo", "onItemSelected:$selectedUnit")
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Empty implementation
                }
            }
        binding.btnSubmit.setOnClickListener {
            if (binding.edtUnitFrom.text.length==0){
                Toast.makeText(requireContext(), "Enter No", Toast.LENGTH_SHORT).show()
            }
            else{
                Log.d("You clicked Unit", "value $binding.edtUnitFrom.text.toString(),uFrom:$uFrom , uTo:$uTo")
                binding.edtUnitTo.text=
                    convertingLogic.resetLengthunits(binding.edtUnitFrom.text.toString().toDouble(),uFrom.toString(),uTo.toString())
                        .toString()

            }
        }


        return binding.root
    }
}
*/
package com.example.utilityapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.utilityapplication.convertingLogic.Lengthunits
import com.example.utilityapplication.convertingLogic.fromData
import com.example.utilityapplication.databinding.FragmentBmicalculatorfragmentBinding

class BmiCalculatorFragment : Fragment() {
//    var fromData:Double=0.0
    private lateinit var binding: FragmentBmicalculatorfragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBmicalculatorfragmentBinding.inflate(inflater, container, false)

        val arrayAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            Lengthunits
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
                    val selectedUnit = Lengthunits[position]
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
                    val selectedUnit = Lengthunits[position]
                    binding.edtUnitTo.hint = selectedUnit
                    Log.d("You clicked UnitTo", "onItemSelected:$selectedUnit")
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Empty implementation
                }
            }

        binding.btnSubmit.setOnClickListener {
            fromData = binding.edtUnitFrom.text.toString().toDouble()
            if (fromData == null) {
                Toast.makeText(requireContext(), "Enter a valid number", Toast.LENGTH_SHORT).show()
            } else {
                val fromUnit = Lengthunits[binding.spinnerUnitFrom.selectedItemPosition]
                val toUnit = Lengthunits[binding.spinnerUnitTo.selectedItemPosition]
                val result = convertingLogic.resetUnits(fromData, fromUnit, toUnit)
                Log.d("You clicked Unit", "fromData:$fromData, fromUnit:$fromUnit, toUnit:$toUnit")
                binding.edtUnitTo.setText(result.toString())
            }
        }

        return binding.root
    }
}
