package com.example.cal

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.cal.historyRoom.HistoryListAdapter
import com.example.cal.historyRoom.HistoryViewModel
import com.example.cal.historyRoom.Record
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.record_view_holder.*
import java.time.LocalDate

class MainActivity : AppCompatActivity() {

    private  lateinit var calTextView: TextView
    private lateinit var massEditText: EditText
    private lateinit var heightEditText: EditText

    lateinit var historyViewModel: HistoryViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = getIntent()
        val userName = intent.getStringExtra("userName")
        val userGender = intent.getStringExtra("userGender")

        calTextView = findViewById(R.id.cal_textView)
        massEditText = findViewById(R.id.mass_editText)
        heightEditText = findViewById(R.id.height_editText)

        var historyButton = findViewById<Button>(R.id.checkHistory_button)
        historyButton.setOnClickListener {
            val intent = Intent(this, HistoryCheckActivity::class.java)
            startActivity(intent)
        }

        historyViewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)

        var addRecordButton = findViewById<Button>(R.id.addToHistory_button)
        addRecordButton.setOnClickListener {
            val bmi = calculate_demand(userGender).toInt().toString()
            val time = LocalDate.now().toString()
            historyViewModel.insert(Record(bmi, time))
            Unit
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

    private fun calculate_demand(gender: String): Double {
        var demand: Double = 0.0
        var coefficient: Double = 0.0
        if (gender == "Mężczyzna") {
            coefficient = 5.0
        } else if (gender == "Kobieta") {
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
