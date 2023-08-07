package com.example.utilityapplication

import android.widget.Toast

object convertingLogic {
    var UnitType = ""
    var fromData: Double = 0.0
    val AgeLimits = arrayOf("0",
        "1", "2", "3", "4", "5",
        "6", "7", "8", "9", "10",
        "11", "12", "13", "14", "15",
        "16", "17", "18", "19", "20",
        "21", "22", "23", "24", "25",
        "26", "27", "28", "29", "30",
        "31", "32", "33", "34", "35",
        "36", "37", "38", "39", "40",
        "41", "42", "43", "44", "45",
        "46", "47", "48", "49", "50",
        "51", "52", "53", "54", "55",
        "56", "57", "58", "59", "60",
        "61", "62", "63", "64", "65",
        "66", "67", "68", "69", "70",
        "71", "72", "73", "74", "75",
        "76", "77", "78", "79", "80",
        "81", "82", "83", "84", "85",
        "86", "87", "88", "89", "90",
        "91", "92", "93", "94", "95",
        "96", "97", "98", "99", "100",
        "101", "102", "103", "104", "105",
        "106", "107", "108", "109", "110",
        "111", "112", "113", "114", "115",
        "116", "117", "118", "119", "120"
    )

    val WeightUnits = arrayOf(
        "Kilogram (kg)", "Gram (g)", "Milligram (mg)", "Pound (lb)", "Ounce (oz)"
    )
    val Lengthunits = arrayOf(
        "Meter (m)", "Kilometer (km)", "Centimeter (cm)", "Millimeter (mm)",
        "Inch (in)", "Foot (ft)", "Yard (yd)", "Mile (mi)"
    )
    val TemperatureUnits = arrayOf(
        "Celsius (°C)", "Fahrenheit (°F)", "Kelvin (K)"
    )
    val VolumeUnits = arrayOf(
        "Liter (L)", "Milliliter (mL)", "Cubic meter (m³)", "Cubic centimeter (cm³)",
        "Gallon (gal)", "Quart (qt)", "Pint (pt)", "Fluid ounce (fl oz)"
    )
    val AreaUnits = arrayOf(
        "Square meter (m²)",
        "Square kilometer (km²)",
        "Square centimeter (cm²)",
        "Square inch (in²)",
        "Square foot (ft²)",
        "Acre (ac)",
        "Hectare (ha)"
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


    val unitsMap = mapOf(
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

    fun convertWeightToKilograms(weight: Double, weightunit: String): Double {
        return when (weightunit) {
            "kg" -> weight
            "g" -> weight / 1000
            "mg" -> weight / 1_000_000
            "lb" -> weight * 0.453592
            "oz" -> weight * 0.0283495
            else -> weight * 0
        }
    }

    //---------------------------------------

    fun convertLengthToCentimeters(length: Double, lengthunit: String): Double {
        return when (lengthunit) {
            "cm" -> length
            "m" -> length * 100
            "km" -> length * 100_000
            "mm" -> length / 0.1
            "in" -> length * 2.54
            "ft" -> length * 30.48
            "yd" -> length * 91.44
            "mi" -> length * 160_934.4
            else -> length * 0
        }
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
                "kcal" to value * 0.000239006,
                "cal" to value * 0.239006
            ),
            "kcal" to mapOf(
                "J" to value * 4184.0,
                "cal" to value * 1000.0
            ),
            "cal" to mapOf(
                "J" to value * 4.184,
                "kcal" to value * 0.001
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
                "f" to (value * 9 / 5) + 32,
                "k" to value + 273.15
            ),
            "f" to mapOf(
                "c" to ((value - 32) * 5) / 9,
                "k" to ((value + 459.67) * 5) / 9
            ),
            "k" to mapOf(
                "c" to value - 273.15,
                "f" to (value * 9 / 5) - 459.67
            ),

            // Volume conversions---------------------------------------
            "L" to mapOf(
                "mL" to value * 1000.0,
                "m³" to value * 0.001,
                "cm³" to value * 1000.0,
                "gal" to value * 0.264172,
                "qt" to value * 1.05669,
                "pt" to value * 2.11338,
                "fl oz" to value * 33.814
            ),
            "mL" to mapOf(
                "L" to value * 0.001,
                "m³" to value * 1e-6,
                "cm³" to value,
                "gal" to value * 0.000264172,
                "qt" to value * 0.00105669,
                "pt" to value * 0.00211338,
                "fl oz" to value * 0.033814
            ),
            "m³" to mapOf(
                "L" to value * 1000.0,
                "mL" to value * 1e+6,
                "cm³" to value * 1e+6,
                "gal" to value * 264.172,
                "qt" to value * 1056.69,
                "pt" to value * 2113.38,
                "fl oz" to value * 33814.0
            ),
            "cm³" to mapOf(
                "L" to value * 0.001,
                "mL" to value,
                "m³" to value * 1e-6,
                "gal" to value * 2.64172e-4,
                "qt" to value * 1.05669e-3,
                "pt" to value * 2.11338e-3,
                "fl oz" to value * 3.3814e-2
            ),
            "gal" to mapOf(
                "L" to value * 3.78541,
                "mL" to value * 3785.41,
                "m³" to value * 0.00378541,
                "cm³" to value * 3785.41,
                "qt" to value * 4.0,
                "pt" to value * 8.0,
                "fl oz" to value * 128.0
            ),
            "qt" to mapOf(
                "L" to value * 0.946353,
                "mL" to value * 946.353,
                "m³" to value * 9.46353e-4,
                "cm³" to value * 946.353,
                "gal" to value * 0.25,
                "pt" to value * 2.0,
                "fl oz" to value * 32.0
            ),
            "pt" to mapOf(
                "L" to value * 0.473176,
                "mL" to value * 473.176,
                "m³" to value * 4.73176e-4,
                "cm³" to value * 473.176,
                "gal" to value * 0.125,
                "qt" to value * 0.5,
                "fl oz" to value * 16.0
            ),
            "fl oz" to mapOf(
                "L" to value * 0.0295735,
                "mL" to value * 29.5735,
                "m³" to value * 2.95735e-5,
                "cm³" to value * 29.5735,
                "gal" to value * 0.0078125,
                "qt" to value * 0.03125,
                "pt" to value * 0.0625
            ),

            //Area conversions---------------------------------------
            "m²" to mapOf(
                "km²" to value * 1e-6,
                "cm²" to value * 10000.0,
                "in²" to value * 1550.0031,
                "ft²" to value * 10.7639,
                "ac" to value * 0.000247105,
                "ha" to value * 1e-4
            ),
            "km²" to mapOf(
                "m²" to value * 1e+6,
                "cm²" to value * 1e+10,
                "in²" to value * 1.55e+9,
                "ft²" to value * 1.076e+7,
                "ac" to value * 247.105,
                "ha" to value * 100.0
            ),
            "cm²" to mapOf(
                "m²" to value * 1e-4,
                "km²" to value * 1e-10,
                "in²" to value * 0.155,
                "ft²" to value * 0.00107639,
                "ac" to value * 2.47105e-8,
                "ha" to value * 1e-8
            ),
            "in²" to mapOf(
                "m²" to value * 0.00064516,
                "km²" to value * 6.4516e-10,
                "cm²" to value * 6.4516,
                "ft²" to value * 0.00694444,
                "ac" to value * 1.5942e-7,
                "ha" to value * 6.4516e-8
            ),
            "ft²" to mapOf(
                "m²" to value * 0.092903,
                "km²" to value * 9.2903e-8,
                "cm²" to value * 929.0304,
                "in²" to value * 144.0,
                "ac" to value * 2.2957e-5,
                "ha" to value * 9.2903e-6
            ),
            "ac" to mapOf(
                "m²" to value * 4046.856,
                "km²" to value * 4.046856e-6,
                "cm²" to value * 4.046856e+7,
                "in²" to value * 6.273e+6,
                "ft²" to value * 43560.0,
                "ha" to value * 0.404686
            ),
            "ha" to mapOf(
                "m²" to value * 10000.0,
                "km²" to value * 1e-4,
                "cm²" to value * 1e+8,
                "in²" to value * 1.55e+7,
                "ft²" to value * 107639.0,
                "ac" to value * 2.47105
            ),

            // Speed conversions---------------------------------------
            "m/s" to mapOf(
                "km/h" to value * 3.6,
                "mph" to value * 2.23694
            ),
            "km/h" to mapOf(
                "m/s" to value * 0.277778,
                "mph" to value * 0.621371
            ),
            "mph" to mapOf(
                "m/s" to value * 0.44704,
                "km/h" to value * 1.60934
            ),

            // Time conversions---------------------------------------
            "s" to mapOf(
                "min" to value / 60.0,
                "hr" to value / 3600.0,
                "day" to value / 86400.0
            ),
            "min" to mapOf(
                "s" to value * 60.0,
                "hr" to value / 60.0,
                "day" to value / 1440.0
            ),
            "hr" to mapOf(
                "s" to value * 3600.0,
                "min" to value * 60.0,
                "day" to value / 24.0
            ),
            "day" to mapOf(
                "s" to value * 86400.0,
                "min" to value * 1440.0,
                "hr" to value * 24.0
            )
        )

        val conversionFactor = conversionFactors[fromUnit]?.get(toUnit)
        return if (conversionFactor != null) {
            value * conversionFactor.toString().toDouble()
        } else {
            value // If no conversion is found, return the original value
        }

    }

    //------------------------------------------------------------
    fun getUnitArray(unitType: String): Array<String> {
        return when (unitType) {
            "length" -> Lengthunits
            "weight" -> WeightUnits
            "temperature" -> TemperatureUnits
            "volume" -> VolumeUnits
            "area" -> AreaUnits
            "speed" -> SpeedUnits
            "time" -> TimeUnits
            "energy" -> EnergyUnits
            "age" -> AgeLimits
            else -> emptyArray()
        }
    }
}
