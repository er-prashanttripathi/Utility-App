package com.example.utilityapplication

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.utilityapplication.convertingLogic.convertLengthToCentimeters
import com.example.utilityapplication.convertingLogic.convertWeightToKilograms
import com.example.utilityapplication.convertingLogic.getUnitArray
import com.example.utilityapplication.convertingLogic.unitsMap
import com.example.utilityapplication.databinding.FragmentBmicalculatorfragmentBinding
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.text.DecimalFormat

class BmiCalculatorFragment : Fragment() {

    private var unitTypeFrom = "length"
    private var unitTypeTo = "weight"
    private var unitTypeAge = "age"
    private var bmilength = 0.0
    private var bmiweight = 0.0
    private var bmiage: Int = 0
    private var bmigender: Boolean = true

    private lateinit var binding: FragmentBmicalculatorfragmentBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBmicalculatorfragmentBinding.inflate(inflater, container, false)

        val arrayAdapterFrom = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            getUnitArray(unitTypeFrom)
                .toList() // Change Array<String> to List<String>
        )
        val arrayAdapterTo = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            getUnitArray(unitTypeTo)
                .toList() // Change Array<String> to List<String>
        )

        val arrayAdapterAge = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            getUnitArray(unitTypeAge)
        )
        binding.apply {
            btnclosereport.setOnClickListener {
                reportcard.visibility=View.INVISIBLE
            }
            spinnerUnitFrom.adapter = arrayAdapterFrom
            spinnerUnitTo.adapter = arrayAdapterTo
            spinnerAge.adapter = arrayAdapterAge

            spinnerUnitFrom.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        val selectedUnit = getUnitArray(unitTypeFrom)[position]
                        edtLength.hint = selectedUnit
                        Log.d("You clickked UnitFrom", "onItemSelected:$selectedUnit ")
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        // Empty implementation
                    }
                }

            spinnerUnitTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedUnit = getUnitArray(unitTypeTo)[position]
                    edtWeight.hint = selectedUnit
                    Log.d("You clicked UnitTo", "onItemSelected:$selectedUnit")
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Empty implementation
                }
            }

            spinnerAge.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedUnit = getUnitArray(unitTypeAge)[position]
                    edtAge.text = selectedUnit
                    Log.d("You clicked UnitAge", "onItemSelected:$selectedUnit")
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Empty implementation
                }
            }

            btnSubmit.setOnClickListener {
                if (binding.edtLength.text.isNullOrBlank() || binding.edtWeight.text.isNullOrBlank() || binding.edtAge.text.isNullOrBlank()) {
                    Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT)
                        .show()

                }
                else {
//                    Toast.makeText(requireContext(), "Values filled", Toast.LENGTH_SHORT).show()

                    bmilength = binding.edtLength.text.toString().toDoubleOrNull() ?: 0.0
                    bmiweight = binding.edtWeight.text.toString().toDoubleOrNull() ?: 0.0
                    bmiage = binding.edtAge.text.toString().toIntOrNull() ?: 0
                    // The ?: operator above provides a default value of 0.0 for the doubles and 0 for the integer if parsing fails.

                    // ... (previous code remains the same)
                    myradiogroup.setOnCheckedChangeListener { group, checkedId ->
                        if (radiomale.isChecked) {
                            bmigender = true
                        } else {
                            bmigender = false
                        }

                    }

                    val mto =
                        unitsMap[getUnitArray(unitTypeTo)[spinnerUnitTo.selectedItemPosition]].toString()//weight
                    val mfrom =
                        unitsMap[getUnitArray(unitTypeFrom)[spinnerUnitFrom.selectedItemPosition]].toString()//length
                    val mage = getUnitArray(unitTypeAge)[spinnerAge.selectedItemPosition].toInt()

                    var nfrom = convertLengthToCentimeters(bmilength, mfrom)
                    var nto = convertWeightToKilograms(bmiweight, mto)

                    var nbmi = calculateBMI(nto, nfrom)
                    var nbmiage = categorizeBMIForAge(nbmi, mage, bmigender)
                /*    bmivalue.text = nbmi.toString()
                    bmicategory.text = nbmiage*/
                    txtbmivalue.text=nbmi.toFloat().toString()
                    txtweightvalue.text=bmiweight.toString()
                    txtheightvalue.text=bmilength.toString()
                    txtagetvalue.text=mage.toString()
                    if (bmigender==true){
                        txtgendertvalue.text="Male"
                    }
                    else{
                        txtgendertvalue.text="Female"
                    }


                    txtidealbmivalue.text= "18.5 - 24.9"
                    var bmilefrom=(18.6*(bmilength/100)*(bmilength/100)).toFloat().toString()
                    var bmileto=(24.8*(bmilength/100)*(bmilength/100)).toFloat().toString()
                    txtidealweightvalue.text=(bmilefrom)+" to "+(bmileto)

                    reportcard.visibility=View.VISIBLE
                    requestStoragePermissions()
                    val cardView = binding.reportcard
                    val captureButton = binding.btnsaveasimage
                    captureButton.setOnClickListener {
                        val bitmap = takeScreenshot(cardView)
                        if (bitmap != null) {
                            saveScreenshotToGallery(bitmap,requireActivity())
                        }
                    }
                }

            }





            return root
        }

    }

    private fun saveScreenshotToGallery(bitmap: Bitmap,context:Context) {
        val filename = "${System.currentTimeMillis()}.jpg"
        var fos: OutputStream? = null

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            context.contentResolver?.also { resolver ->
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }
                val imageUri: Uri? =
                    resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                fos = imageUri?.let { resolver.openOutputStream(it) }
            }
        } else {
            val imagesDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imagesDir, filename)
            fos = FileOutputStream(image)
        }

        fos?.use {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            Toast.makeText(requireActivity(), "Captured View and saved to Gallery", Toast.LENGTH_SHORT).show()
        }
    }

    private fun takeScreenshot(view: View): Bitmap? {
        var screenshot: Bitmap? = null
        try {
            screenshot = Bitmap.createBitmap(
                view.measuredWidth,
                view.measuredHeight,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(screenshot)
            view.draw(canvas)
        } catch (e: Exception) {
            Log.e("GFG", "Failed to capture screenshot because: ${e.message}")
        }
        return screenshot
    }

    private fun requestStoragePermissions() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ),
            1
        )
    }

    private fun calculateBMI(weightKg: Double, heightCm: Double): Double {
        val heightMeters = heightCm / 100
        return weightKg / (heightMeters * heightMeters)
    }

    private fun categorizeBMIForAge(bmi: Double, ageYears: Int, isMale: Boolean): String {
        return if (ageYears < 18) {
            categorizeBMIForChildrenAndAdolescents(bmi, ageYears, isMale)
        } else {
            categorizeBMIForAdults(bmi)
        }
    }

    private fun categorizeBMIForChildrenAndAdolescents(
        bmi: Double,
        ageYears: Int,
        isMale: Boolean
    ): String {
        // Categorization logic for BMI in children and adolescents (age < 18)
        // Implement your own categorization logic based on age, gender, and BMI values
        // For simplicity, let's use a simple categorization below:
        val category: String
        if (bmi < 18.5) {
            category = "Underweight"
        } else if (bmi < 24.9) {
            category = "Normal weight"
        } else if (bmi < 29.9) {
            category = "Overweight"
        } else {
            category = "Obese"
        }
        return category
    }

    private fun categorizeBMIForAdults(bmi: Double): String {
        // Categorization logic for BMI in adults (age >= 18)
        // Implement your own categorization logic based on BMI values
        // For simplicity, let's use a simple categorization below:
        val category: String
        if (bmi < 18.5) {
            category = "Underweight"
        } else if (bmi < 24.9) {
            category = "Normal weight"
        } else if (bmi < 29.9) {
            category = "Overweight"
        } else {
            category = "Obese"
        }
        return category
    }
}
