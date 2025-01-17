package com.example.cal

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.example.cal.userRoom.User
import com.example.cal.userRoom.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_new_user.*


class NewUserActivity : AppCompatActivity() {

    lateinit var newUserEditText: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user)
        newUserEditText = findViewById(R.id.newUser_editText)

        val userGender = findViewById<RadioGroup>(R.id.usergender_radioGroup)

        var gender: String = ""
        userGender.setOnCheckedChangeListener { group, checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            gender = radio.text.toString()
        }

        val newUserButton = findViewById<Button>(R.id.addUser_button)
        newUserButton.setOnClickListener(){
            val replyIntent = Intent(this, StartActivity::class.java)
            if(TextUtils.isEmpty(newUserEditText.text) || userGender.checkedRadioButtonId == -1){
                Toast.makeText(
                    applicationContext,
                    "Uzupełnij nazwę lub wybierz płeć",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val user = newUserEditText.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, user)
                replyIntent.putExtra("GENDER", gender)
                setResult(Activity.RESULT_OK, replyIntent)
                finish()
            }

        }

    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}
