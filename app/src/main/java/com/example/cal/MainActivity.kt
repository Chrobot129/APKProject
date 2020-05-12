package com.example.cal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private  lateinit var calTextView: TextView
    private lateinit var massEditText: EditText
    private lateinit var heightEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calTextView = findViewById(R.id.cal_textView)
        massEditText = findViewById(R.id.mass_editText)
        heightEditText = findViewById(R.id.height_editText)

        massEditText.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                calTextView.text = getString(R.string.demand_string, calculate_demand().toInt())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        heightEditText.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                calTextView.text = getString(R.string.demand_string, calculate_demand().toInt())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        }

    private fun calculate_demand(): Double {
        var demand: Double = 0.0
        var coefficient: Double = 0.0
        if (man_radioButton.isChecked) {
            coefficient = 5.0
        } else if (woman_radioButton.isChecked) {
            coefficient = -161.0
        }
        var mass = 0.0
        var height = 0.0
        if(massEditText.text.isNotEmpty()){
            mass = massEditText.text.toString().toDouble()
        }
        if(heightEditText.text.isNotEmpty()){
            height = heightEditText.text.toString().toDouble()
        }

        demand = 9.99*mass+ 6.25*height  + coefficient

        if(demand < 0.0){
            demand = 0.0
        }

        return demand
    }

}
