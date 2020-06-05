package com.example.cal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.cal.historyRoom.HistoryViewModel
import com.example.cal.historyRoom.Record
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate

class MainActivity : AppCompatActivity() {

    private lateinit var historyViewModel: HistoryViewModel

    private  lateinit var calTextView: TextView
    private lateinit var massEditText: EditText
    private lateinit var heightEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val Intent = getIntent()
        val userName = Intent.getStringExtra("userName")
        val userGender = Intent.getStringExtra("gender")

        calTextView = findViewById(R.id.cal_textView)
        massEditText = findViewById(R.id.mass_editText)
        heightEditText = findViewById(R.id.height_editText)

        historyViewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)

        var historyButton = findViewById<Button>(R.id.checkHistory_button)
        historyButton.setOnClickListener {
            val intent = Intent(this, HistoryCheckActivity::class.java)
            intent.putExtra("userName", userName)
            intent.putExtra("gender", userGender)
            startActivity(intent)
        }

        var addRecordButton = findViewById<Button>(R.id.addRecord_button)
        addRecordButton.setOnClickListener {
            historyViewModel.insert(Record(calTextView.text.toString(), "now"))//"Adrian",
        }

        var gButton = findViewById<Button>(R.id.g_button)
        gButton.setOnClickListener {
        //    val his = historyViewModel.getHistory("dzis")
        //    Toast.makeText(this, "BMI: ${his?.first()?.time}", Toast.LENGTH_LONG).show()
        }

        massEditText.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                calTextView.text = getString(R.string.demand_string, calculate_demand(userGender).toInt())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        heightEditText.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                calTextView.text = getString(R.string.demand_string, calculate_demand(userGender).toInt())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        }

    private fun calculate_demand(userGender: String): Double {
        var demand: Double = 0.0
        var coefficient: Double = 0.0
        if ( userGender == "Mężczyzna" ){  //man_radioButton.isChecked) {
            coefficient = 5.0
        } else if ( userGender == "Kobieta" ){ //woman_radioButton.isChecked) {
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
