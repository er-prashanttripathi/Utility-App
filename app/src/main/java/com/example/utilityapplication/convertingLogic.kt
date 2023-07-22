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
*//*

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
            fromUnit == "in" && toUnit == "m" -> value / 39.37
            fromUnit == "in" && toUnit == "km" -> value * 1000
            fromUnit == "in" && toUnit == "cm" -> value * 2.54
            fromUnit == "in" && toUnit == "mm" -> value * 25.4
            fromUnit == "in" && toUnit == "ft" -> value / 12.0
            fromUnit == "in" && toUnit == "yd" -> value / 36.0
            fromUnit == "in" && toUnit == "mi" -> value * 1.609
            //-----------------------Foot----------------------
            fromUnit == "ft" && toUnit == "m" -> value * 3.281
            fromUnit == "ft" && toUnit == "km" -> value / 3281
            fromUnit == "ft" && toUnit == "cm" -> value * 30.48
            fromUnit == "ft" && toUnit == "mm" -> value * 304.8
            fromUnit == "ft" && toUnit == "in" -> value * 12
            fromUnit == "ft" && toUnit == "yd" -> value / 3.0
            fromUnit == "ft" && toUnit == "mi" -> value / 5280.0
            //-----------------------Yard----------------------
            fromUnit == "yd" && toUnit == "m" -> value / 1.094
            fromUnit == "yd" && toUnit == "km" -> value / 1094.0
            fromUnit == "yd" && toUnit == "cm" -> value * 91.44
            fromUnit == "yd" && toUnit == "mm" -> value * 914.4
            fromUnit == "yd" && toUnit == "in" -> value * 36.0
            fromUnit == "yd" && toUnit == "ft" -> value * 3.0
            fromUnit == "yd" && toUnit == "mi" -> value / 1760.0

//===================================================================================
            // Add more length conversions here...

            // Weight
            fromUnit == "kg" && toUnit == "g" -> value * 1000.0
            fromUnit == "kg" && toUnit == "lb" -> value * 2.20462
            fromUnit == "kg" && toUnit == "oz" -> value * 35.274
            fromUnit == "g" && toUnit == "kg" -> value / 1000.0
            fromUnit == "lb" && toUnit == "kg" -> value / 2.20462
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
*/
package com.example.utilityapplication

object convertingLogic {
    var fromData: Double = 0.0
    val Lengthunits = arrayOf(
        "Meter (m)", "Kilometer (km)", "Centimeter (cm)", "Millimeter (mm)",
        "Inch (in)", "Foot (ft)", "Yard (yd)", "Mile (mi)"
    )
    val WeightUnits = arrayOf(
        "Kilogram (kg)", "Gram (g)", "Milligram (mg)", "Pound (lb)", "Ounce (oz)"
    )
    val TemperatureUnits = arrayOf(
        "Celsius (°C)", "Fahrenheit (°F)", "Kelvin (K)"
    )
    val VolumeUnits = arrayOf(
        "Liter (L)", "Milliliter (mL)", "Cubic meter (m³)", "Cubic centimeter (cm³)",
        "Gallon (gal)", "Quart (qt)", "Pint (pt)", "Fluid ounce (fl oz)"
    )
    val AreaUnits = arrayOf(
        "Square meter (m²)", "Square kilometer (km²)", "Square centimeter (cm²)", "Square inch (in²)",
        "Square foot (ft²)", "Acre (ac)", "Hectare (ha)"
    )
    val SpeedUnits = arrayOf(
        "Meter per second (m/s)", "Kilometer per hour (km/h)", "Mile per hour (mph)"
    )
    val TimeUnits = arrayOf(
        "Second (s)", "Minute (min)", "Hour (hr)", "Day (day)"
    )
    val EnergyUnits = arrayOf(
        "Joule (J)", "Kilocalorie (kcal)", "Calorie (cal)"
    )

    /*  private val unitsMap = mapOf(
        "Meter (m)" to "m",
        "Kilometer (km)" to "km",
        "Centimeter (cm)" to "cm",
        "Millimeter (mm)" to "mm",
        "Inch (in)" to "in",
        "Foot (ft)" to "ft",
        "Yard (yd)" to "yd",
        "Mile (mi)" to "mi"
    )*/
  private val unitsMap = mapOf(
      // Length
      "Meter (m)" to "m",
      "Kilometer (km)" to "km",
      "Centimeter (cm)" to "cm",
      "Millimeter (mm)" to "mm",
      "Inch (in)" to "in",
      "Foot (ft)" to "ft",
      "Yard (yd)" to "yd",
      "Mile (mi)" to "mi",
      // Weight
      "Kilogram (kg)" to "kg",
      "Gram (g)" to "g",
      "Milligram (mg)" to "mg",
      "Pound (lb)" to "lb",
      "Ounce (oz)" to "oz",
      // Temperature
      "Celsius (°C)" to "c",
      "Fahrenheit (°F)" to "f",
      "Kelvin (K)" to "k",
      // Volume
      "Liter (L)" to "L",
      "Milliliter (mL)" to "mL",
      "Cubic meter (m³)" to "m³",
      "Cubic centimeter (cm³)" to "cm³",
      "Gallon (gal)" to "gal",
      "Quart (qt)" to "qt",
      "Pint (pt)" to "pt",
      "Fluid ounce (fl oz)" to "fl oz",
      // Area
      "Square meter (m²)" to "m²",
      "Square kilometer (km²)" to "km²",
      "Square centimeter (cm²)" to "cm²",
      "Square inch (in²)" to "in²",
      "Square foot (ft²)" to "ft²",
      "Acre (ac)" to "ac",
      "Hectare (ha)" to "ha",
      // Speed
      "Meter per second (m/s)" to "m/s",
      "Kilometer per hour (km/h)" to "km/h",
      "Mile per hour (mph)" to "mph",
      // Time
      "Second (s)" to "s",
      "Minute (min)" to "min",
      "Hour (hr)" to "hr",
      "Day (day)" to "day",
      // Energy
      "Joule (J)" to "J",
      "Kilocalorie (kcal)" to "kcal",
      "Calorie (cal)" to "cal"
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
        val conversionFactors = mapOf(
            // Length conversions---------------------------------------
            "m" to mapOf(
                "km" to 0.001,
                "cm" to 100.0,
                "mm" to 1000.0,
                "in" to 39.3701,
                "ft" to 3.281,
                "yd" to 1.094,
                "mi" to 0.000621371
            ),
            "km" to mapOf(
                "m" to 1000.0,
                "cm" to 100000.0,
                "mm" to 1000000.0,
                "in" to 39370.1,
                "ft" to 3281.0,
                "yd" to 1093.61,
                "mi" to 0.621371
            ),
            "cm" to mapOf(
                "m" to 0.01,
                "km" to 0.00001,
                "mm" to 10.0,
                "in" to 0.393701,
                "ft" to 0.0328084,
                "yd" to 0.0109361,
                "mi" to 6.2137e-6
            ),
            "mm" to mapOf(
                "m" to 0.001,
                "km" to 1e-6,
                "cm" to 0.1,
                "in" to 0.0393701,
                "ft" to 0.00328084,
                "yd" to 0.00109361,
                "mi" to 6.2137e-7
            ),
            "in" to mapOf(
                "m" to 0.0254,
                "km" to 0.0000254,
                "cm" to 2.54,
                "mm" to 25.4,
                "ft" to 0.0833333,
                "yd" to 0.0277778,
                "mi" to 1.60934e-5
            ),
            "ft" to mapOf(
                "m" to 0.3048,
                "km" to 0.0003048,
                "cm" to 30.48,
                "mm" to 304.8,
                "in" to 12.0,
                "yd" to 0.333333,
                "mi" to 1.89394e-4
            ),
            "yd" to mapOf(
                "m" to 0.9144,
                "km" to 0.0009144,
                "cm" to 91.44,
                "mm" to 914.4,
                "in" to 36.0,
                "ft" to 3.0,
                "mi" to 5.68182e-4
            ),
            "mi" to mapOf(
                "m" to 1609.34,
                "km" to 1.60934,
                "cm" to 160934.0,
                "mm" to 1609340.0,
                "in" to 63360.0,
                "ft" to 5280.0,
                "yd" to 1760.0
            ),
            // Energy conversions---------------------------------------
            "J" to mapOf(
                "kcal" to { x: Double -> x * 0.000239006 },
                "cal" to { x: Double -> x * 0.239006 }
            ),
            "kcal" to mapOf(
                "J" to { x: Double -> x * 4184.0 },
                "cal" to { x: Double -> x * 1000.0 }
            ),
            "cal" to mapOf(
                "J" to { x: Double -> x * 4.184 },
                "kcal" to { x: Double -> x * 0.001 }
            ),

            // Weight conversions---------------------------------------
            "kg" to mapOf(
                "g" to 1000.0,
                "lb" to 2.20462,
                "oz" to 35.274
            ),
            "g" to mapOf(
                "kg" to 0.001,
                "lb" to 0.00220462,
                "oz" to 0.035274
            ),
            "lb" to mapOf(
                "kg" to 0.453592,
                "g" to 453.592,
                "oz" to 16.0
            ),
            "oz" to mapOf(
                "kg" to 0.0283495,
                "g" to 28.3495,
                "lb" to 0.0625
            ),

            // Temperature conversions---------------------------------------
            "c" to mapOf(
                "f" to { x: Double -> (x * 9 / 5) + 32 },
                "k" to { x: Double -> x + 273.15 }
            ),
            "f" to mapOf(
                "c" to { x: Double -> (x - 32) * 5 / 9 },
                "k" to { x: Double -> (x + 459.67) * 5 / 9 }
            ),
            "k" to mapOf(
                "c" to { x: Double -> x - 273.15 },
                "f" to { x: Double -> (x * 9 / 5) - 459.67 }
            ),

            // Volume conversions---------------------------------------
            "L" to mapOf(
                "mL" to { x: Double -> x * 1000.0 },
                "m³" to { x: Double -> x * 0.001 },
                "cm³" to { x: Double -> x * 1000.0 },
                "gal" to { x: Double -> x * 0.264172 },
                "qt" to { x: Double -> x * 1.05669 },
                "pt" to { x: Double -> x * 2.11338 },
                "fl oz" to { x: Double -> x * 33.814 }
            ),
            "mL" to mapOf(
                "L" to { x: Double -> x * 0.001 },
                "m³" to { x: Double -> x * 1e-6 },
                "cm³" to { x: Double -> x },
                "gal" to { x: Double -> x * 0.000264172 },
                "qt" to { x: Double -> x * 0.00105669 },
                "pt" to { x: Double -> x * 0.00211338 },
                "fl oz" to { x: Double -> x * 0.033814 }
            ),
            "m³" to mapOf(
                "L" to { x: Double -> x * 1000.0 },
                "mL" to { x: Double -> x * 1e+6 },
                "cm³" to { x: Double -> x * 1e+6 },
                "gal" to { x: Double -> x * 264.172 },
                "qt" to { x: Double -> x * 1056.69 },
                "pt" to { x: Double -> x * 2113.38 },
                "fl oz" to { x: Double -> x * 33814.0 }
            ),
            "cm³" to mapOf(
                "L" to { x: Double -> x * 0.001 },
                "mL" to { x: Double -> x },
                "m³" to { x: Double -> x * 1e-6 },
                "gal" to { x: Double -> x * 2.64172e-4 },
                "qt" to { x: Double -> x * 1.05669e-3 },
                "pt" to { x: Double -> x * 2.11338e-3 },
                "fl oz" to { x: Double -> x * 3.3814e-2 }
            ),
            "gal" to mapOf(
                "L" to { x: Double -> x * 3.78541 },
                "mL" to { x: Double -> x * 3785.41 },
                "m³" to { x: Double -> x * 0.00378541 },
                "cm³" to { x: Double -> x * 3785.41 },
                "qt" to { x: Double -> x * 4.0 },
                "pt" to { x: Double -> x * 8.0 },
                "fl oz" to { x: Double -> x * 128.0 }
            ),
            "qt" to mapOf(
                "L" to { x: Double -> x * 0.946353 },
                "mL" to { x: Double -> x * 946.353 },
                "m³" to { x: Double -> x * 9.46353e-4 },
                "cm³" to { x: Double -> x * 946.353 },
                "gal" to { x: Double -> x * 0.25 },
                "pt" to { x: Double -> x * 2.0 },
                "fl oz" to { x: Double -> x * 32.0 }
            ),
            "pt" to mapOf(
                "L" to { x: Double -> x * 0.473176 },
                "mL" to { x: Double -> x * 473.176 },
                "m³" to { x: Double -> x * 4.73176e-4 },
                "cm³" to { x: Double -> x * 473.176 },
                "gal" to { x: Double -> x * 0.125 },
                "qt" to { x: Double -> x * 0.5 },
                "fl oz" to { x: Double -> x * 16.0 }
            ),
            "fl oz" to mapOf(
                "L" to { x: Double -> x * 0.0295735 },
                "mL" to { x: Double -> x * 29.5735 },
                "m³" to { x: Double -> x * 2.95735e-5 },
                "cm³" to { x: Double -> x * 29.5735 },
                "gal" to { x: Double -> x * 0.0078125 },
                "qt" to { x: Double -> x * 0.03125 },
                "pt" to { x: Double -> x * 0.0625 }
            ),

            //Area conversions---------------------------------------
            "m²" to mapOf(
                "km²" to { x: Double -> x * 1e-6 },
                "cm²" to { x: Double -> x * 10000.0 },
                "in²" to { x: Double -> x * 1550.0031 },
                "ft²" to { x: Double -> x * 10.7639 },
                "ac" to { x: Double -> x * 0.000247105 },
                "ha" to { x: Double -> x * 1e-4 }
            ),
            "km²" to mapOf(
                "m²" to { x: Double -> x * 1e+6 },
                "cm²" to { x: Double -> x * 1e+10 },
                "in²" to { x: Double -> x * 1.55e+9 },
                "ft²" to { x: Double -> x * 1.076e+7 },
                "ac" to { x: Double -> x * 247.105 },
                "ha" to { x: Double -> x * 100.0 }
            ),
            "cm²" to mapOf(
                "m²" to { x: Double -> x * 1e-4 },
                "km²" to { x: Double -> x * 1e-10 },
                "in²" to { x: Double -> x * 0.155 },
                "ft²" to { x: Double -> x * 0.00107639 },
                "ac" to { x: Double -> x * 2.47105e-8 },
                "ha" to { x: Double -> x * 1e-8 }
            ),
            "in²" to mapOf(
                "m²" to { x: Double -> x * 0.00064516 },
                "km²" to { x: Double -> x * 6.4516e-10 },
                "cm²" to { x: Double -> x * 6.4516 },
                "ft²" to { x: Double -> x * 0.00694444 },
                "ac" to { x: Double -> x * 1.5942e-7 },
                "ha" to { x: Double -> x * 6.4516e-8 }
            ),
            "ft²" to mapOf(
                "m²" to { x: Double -> x * 0.092903 },
                "km²" to { x: Double -> x * 9.2903e-8 },
                "cm²" to { x: Double -> x * 929.0304 },
                "in²" to { x: Double -> x * 144.0 },
                "ac" to { x: Double -> x * 2.2957e-5 },
                "ha" to { x: Double -> x * 9.2903e-6 }
            ),
            "ac" to mapOf(
                "m²" to { x: Double -> x * 4046.856 },
                "km²" to { x: Double -> x * 4.046856e-6 },
                "cm²" to { x: Double -> x * 4.046856e+7 },
                "in²" to { x: Double -> x * 6.273e+6 },
                "ft²" to { x: Double -> x * 43560.0 },
                "ha" to { x: Double -> x * 0.404686 }
            ),
            "ha" to mapOf(
                "m²" to { x: Double -> x * 10000.0 },
                "km²" to { x: Double -> x * 1e-4 },
                "cm²" to { x: Double -> x * 1e+8 },
                "in²" to { x: Double -> x * 1.55e+7 },
                "ft²" to { x: Double -> x * 107639.0 },
                "ac" to { x: Double -> x * 2.47105 }
            ),

            // Speed conversions---------------------------------------
            "m/s" to mapOf(
                "km/h" to { x: Double -> x * 3.6 },
                "mph" to { x: Double -> x * 2.23694 }
            ),
            "km/h" to mapOf(
                "m/s" to { x: Double -> x * 0.277778 },
                "mph" to { x: Double -> x * 0.621371 }
            ),
            "mph" to mapOf(
                "m/s" to { x: Double -> x * 0.44704 },
                "km/h" to { x: Double -> x * 1.60934 }
            ),

            // Time conversions---------------------------------------
            "s" to mapOf(
                "min" to { x: Double -> x / 60.0 },
                "hr" to { x: Double -> x / 3600.0 },
                "day" to { x: Double -> x / 86400.0 }
            ),
            "min" to mapOf(
                "s" to { x: Double -> x * 60.0 },
                "hr" to { x: Double -> x / 60.0 },
                "day" to { x: Double -> x / 1440.0 }
            ),
            "hr" to mapOf(
                "s" to { x: Double -> x * 3600.0 },
                "min" to { x: Double -> x * 60.0 },
                "day" to { x: Double -> x / 24.0 }
            ),
            "day" to mapOf(
                "s" to { x: Double -> x * 86400.0 },
                "min" to { x: Double -> x * 1440.0 },
                "hr" to { x: Double -> x * 24.0 }
            )
        )

        val conversionFactor = conversionFactors[fromUnit]?.get(toUnit)
        return if (conversionFactor != null) {
            value * conversionFactor.toString().toDouble()
        } else {
            value // If no conversion is found, return the original value
        }
    }
}
