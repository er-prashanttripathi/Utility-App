package com.example.utilityapplication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.utilityapplication.databinding.FragmentUnitBinding


class UnitFragment : Fragment() {
    private lateinit var binding: FragmentUnitBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_unit, container, false)
val view=inflater.inflate(R.layout.fragment_unit, container, false)
        // Inflate the layout for this fragment
        binding.apply {
            btnarea.setOnClickListener {
                findNavController().navigate(R.id.action_unitFragment_to_finalActivity)
            }
        }
        return view
    }


}