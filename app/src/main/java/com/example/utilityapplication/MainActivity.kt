package com.example.utilityapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.utilityapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.apply {
            btnBmi.setOnClickListener {
                loadBMIfrag(
                    BmiCalculatorFragment()
                )
            }
            btnUnit.setOnClickListener {
                loadUnitfrag(
                    UnitFragment()
                )
            }

        }
    }
    private fun loadUnitfrag(goUnit:UnitFragment) {
        val ft =supportFragmentManager.beginTransaction()
        ft.replace(R.id.homefraglayout,goUnit)
        ft.commit()
    }

    private fun loadBMIfrag(goBMI:BmiCalculatorFragment) {
        val ft =supportFragmentManager.beginTransaction()
        ft.replace(R.id.homefraglayout,goBMI)
        ft.commit()
    }
}