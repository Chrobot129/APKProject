package com.example.cal

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class NewUserActivity : AppCompatActivity() {

    lateinit var newUserEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user)
        newUserEditText = findViewById(R.id.newUser_editText)

        val newUserButton = findViewById<Button>(R.id.addUser_button)
        newUserButton.setOnClickListener{
            val replyIntent = Intent()
            if(TextUtils.isEmpty(newUserEditText.text)){
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val user = newUserEditText.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, user)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }

        val button = findViewById<Button>(R.id.button2)
        button.setOnClickListener{
            if (TextUtils.isEmpty(newUserEditText.text)){
                Toast.makeText(
                    applicationContext,
                    "empty",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                    applicationContext,
                    newUserEditText.text.toString(),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}
