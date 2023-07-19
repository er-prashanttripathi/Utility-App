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


}*/
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

class BmiCalculatorFragment : Fragment() {
    private lateinit var binding: FragmentBmicalculatorfragmentBinding

    private val units = arrayOf(
        "Meter (m)", "Kilometer (km)", "Centimeter (cm)", "Millimeter (mm)",
        "Inch (in)", "Foot (ft)", "Yard (yd)", "Mile (mi)"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBmicalculatorfragmentBinding.inflate(inflater, container, false)

        val arrayAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            units
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
                    val selectedUnit = units[position]
                    Toast.makeText(
                        requireContext(),
                        "You clicked $selectedUnit",
                        Toast.LENGTH_SHORT
                    ).show()
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
                    val selectedUnit = units[position]
                    Toast.makeText(
                        requireContext(),
                        "You clicked $selectedUnit",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Empty implementation
                }
            }

        return binding.root
    }
}
