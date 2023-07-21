/*
package com.example.utilityapplication

import android.content.Context
import android.widget.EditText

object convertingLogic {

    var fromvalue = ""
    var tovalue = ""

    //--------------------------------------------
    fun resetUnits(value: Double, fromUnit: String, toUnit: String) {
        when {
            fromUnit == "Meter (m)" -> fromvalue = "m"
            fromUnit == "Kilometer (km)" -> fromvalue = "km"
            fromUnit == "Centimeter (cm)" -> fromvalue = "cm"
            fromUnit == "Millimeter (mm)" -> fromvalue = "mm"
            fromUnit == "Inch (in)" -> fromvalue = "in"
            fromUnit == "Foot (ft)" -> fromvalue = "ft"
            fromUnit == "Yard (yd)" -> fromvalue = "yd"
            fromUnit == "Mile (mi)" -> fromvalue = "mi"

            toUnit == "Meter (m)" -> tovalue = "m"
            toUnit == "Kilometer (km)" -> tovalue = "km"
            toUnit == "Centimeter (cm)" -> tovalue = "cm"
            toUnit == "Millimeter (mm)" -> tovalue = "mm"
            toUnit == "Inch (in)" -> tovalue = "in"
            toUnit == "Foot (ft)" -> tovalue = "ft"
            toUnit == "Yard (yd)" -> tovalue = "yd"
            toUnit == "Mile (mi)" -> tovalue = "mi"
        }
        convertUnit(value, fromvalue, tovalue)
    }

    //---------------------------------------
    fun convertUnit(value: Double, fromUnit: String, toUnit: String): Double {
        return when {
            // Length
            fromUnit == "m" && toUnit == "cm" -> value * 100.0
            fromUnit == "cm" && toUnit == "m" -> value / 100.0
            fromUnit == "m" && toUnit == "km" -> value / 1000.0
            fromUnit == "km" && toUnit == "m" -> value * 1000.0
            fromUnit == "in" && toUnit == "cm" -> value * 2.54
            fromUnit == "cm" && toUnit == "in" -> value / 2.54
            // Add more length conversions here...

            // Weight
            fromUnit == "kg" && toUnit == "g" -> value * 1000.0
            fromUnit == "g" && toUnit == "kg" -> value / 1000.0
            fromUnit == "kg" && toUnit == "lb" -> value * 2.20462
            fromUnit == "lb" && toUnit == "kg" -> value / 2.20462
            fromUnit == "kg" && toUnit == "oz" -> value * 35.274
            fromUnit == "oz" && toUnit == "kg" -> value / 35.274
            // Add more weight conversions here...

            // Temperature
            fromUnit == "c" && toUnit == "f" -> (value * 9 / 5) + 32
            fromUnit == "f" && toUnit == "c" -> (value - 32) * 5 / 9
            fromUnit == "c" && toUnit == "k" -> value + 273.15
            fromUnit == "k" && toUnit == "c" -> value - 273.15
            // Add more temperature conversions here...

            // Volume
            // Add volume conversions here...

            // Area
            // Add area conversions here...

            // Speed
            // Add speed conversions here...

            // Time
            // Add time conversions here...

            // Energy
            // Add energy conversions here...

            else ->
                value - 273.15
            // If no conversion is found, return the original value
        }
    }


    //--------------------------------------------
}
*/
package com.example.utilityapplication

import android.widget.EditText

object convertingLogic {
    var fromData: Double = 0.0
    val Lengthunits = arrayOf(
        "Meter (m)", "Kilometer (km)", "Centimeter (cm)", "Millimeter (mm)",
        "Inch (in)", "Foot (ft)", "Yard (yd)", "Mile (mi)"
    )
    private val unitsMap = mapOf(
        "Meter (m)" to "m",
        "Kilometer (km)" to "km",
        "Centimeter (cm)" to "cm",
        "Millimeter (mm)" to "mm",
        "Inch (in)" to "in",
        "Foot (ft)" to "ft",
        "Yard (yd)" to "yd",
        "Mile (mi)" to "mi"
    )

    //--------------------------------------------
    fun resetUnits(value: Double, fromUnit: String, toUnit: String): Double {
        val fromValue = unitsMap[fromUnit]
        val toValue = unitsMap[toUnit]

        if (fromValue != null && toValue != null) {
            return convertUnit(value, fromValue, toValue)
        }

        // If the conversion units are not recognized, return the original value
        return value
    }

    //---------------------------------------
    fun convertUnit(value: Double, fromUnit: String, toUnit: String): Double {
        return when {
            // Length
            //-----------------------Meter----------------------
            fromUnit == "m" && toUnit == "km" -> value / 1000.0
            fromUnit == "m" && toUnit == "cm" -> value * 100.0
            fromUnit == "m" && toUnit == "mm" -> value * 1000.0
            fromUnit == "m" && toUnit == "in" -> value * 39.3701
            fromUnit == "m" && toUnit == "ft" -> value / 3.281
            fromUnit == "m" && toUnit == "yd" -> value * 1.094
            fromUnit == "m" && toUnit == "mi" -> value / 1609
            //-----------------------Kilo-Meter----------------------
            fromUnit == "km" && toUnit == "m" -> value * 1000.0
            fromUnit == "km" && toUnit == "cm" -> value * 100000.0
            fromUnit == "km" && toUnit == "mm" -> value * 1000000.0
            fromUnit == "km" && toUnit == "in" -> value * 39370.1
            fromUnit == "km" && toUnit == "ft" -> value * 3281.0
            fromUnit == "km" && toUnit == "yd" -> value * 1093.61
            fromUnit == "km" && toUnit == "mi" -> value / 1.609
            //-----------------------Centi-Meter----------------------
            fromUnit == "cm" && toUnit == "m" -> value / 100.0
            fromUnit == "cm" && toUnit == "km" -> value / 100000.0
            fromUnit == "cm" && toUnit == "mm" -> value * 10.0
            fromUnit == "cm" && toUnit == "in" -> value / 2.54
            fromUnit == "cm" && toUnit == "ft" -> value * 30.48
            fromUnit == "cm" && toUnit == "yd" -> value / 91.44
            fromUnit == "cm" && toUnit == "mi" -> value / 160900
            //-----------------------Milli-Meter----------------------
            fromUnit == "mm" && toUnit == "m" -> value / 1000.0
            fromUnit == "mm" && toUnit == "km" -> value / 1000000.0
            fromUnit == "mm" && toUnit == "cm" -> value / 10.0
            fromUnit == "mm" && toUnit == "in" -> value / 25.4
            fromUnit == "mm" && toUnit == "ft" -> value / 304.8
            fromUnit == "mm" && toUnit == "yd" -> value / 914.4
            fromUnit == "mm" && toUnit == "mi" -> value / 1609000.0
            //-----------------------Inch----------------------
            fromUnit == "in" && toUnit == "cm" -> value * 2.54
            fromUnit == "cm" && toUnit == "in" -> value / 2.54
            // Add more length conversions here...

            // Weight
            fromUnit == "kg" && toUnit == "g" -> value * 1000.0
            fromUnit == "g" && toUnit == "kg" -> value / 1000.0
            fromUnit == "kg" && toUnit == "lb" -> value * 2.20462
            fromUnit == "lb" && toUnit == "kg" -> value / 2.20462
            fromUnit == "kg" && toUnit == "oz" -> value * 35.274
            fromUnit == "oz" && toUnit == "kg" -> value / 35.274
            // Add more weight conversions here...

            // Temperature
            fromUnit == "c" && toUnit == "f" -> (value * 9 / 5) + 32
            fromUnit == "f" && toUnit == "c" -> (value - 32) * 5 / 9
            fromUnit == "c" && toUnit == "k" -> value + 273.15
            fromUnit == "k" && toUnit == "c" -> value - 273.15
            // Add more temperature conversions here...

            // Volume
            // Add volume conversions here...

            // Area
            // Add area conversions here...

            // Speed
            // Add speed conversions here...

            // Time
            // Add time conversions here...

            // Energy
            // Add energy conversions here...

            else -> value // If no conversion is found, return the original value
        }
    }
}
