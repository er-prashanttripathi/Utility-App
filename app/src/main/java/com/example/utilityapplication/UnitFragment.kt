
package com.example.utilityapplication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.utilityapplication.databinding.FragmentUnitBinding

class UnitFragment : Fragment() {
    private lateinit var binding: FragmentUnitBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_unit, container, false)
        val view = binding.root

        // Set click listener for the button
        binding.btnarea.setOnClickListener {
            // Start FinalActivity when the button is clicked
            val intent = Intent(activity, FinalActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}
