package com.example.cal

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cal.historyRoom.HistoryViewModel
import com.example.cal.userRoom.User
import com.example.cal.userRoom.UserListAdapter
import com.example.cal.userRoom.UserViewModel
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {

    private val newUserActivityRequestCode = 1
    lateinit var username: EditText
    private lateinit var userViewModel: UserViewModel
    private lateinit var historyViewModel: HistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)


        val usersRecyclerView = findViewById<RecyclerView>(R.id.users_recyclerview)
        val adapter = UserListAdapter(this, {user: User -> userClicked(user)})
        usersRecyclerView.adapter = adapter
        usersRecyclerView.layoutManager = LinearLayoutManager(this)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.allUsers.observe(this, Observer { users ->
        users?.let { adapter.setWords(it) }
        })


        clean_Button.setOnClickListener {
            historyViewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)
            historyViewModel.deleteAll()
            userViewModel.deleteAll()
            Unit
        }

        add_button.setOnClickListener {
            val intent = Intent(this@StartActivity, NewUserActivity::class.java)
            startActivityForResult(intent, newUserActivityRequestCode)
        }



    }
    private fun userClicked(item: User) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("userName", item.userName)
        intent.putExtra("userGender", item.gender)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if(requestCode == newUserActivityRequestCode && requestCode == -Activity.RESULT_OK) {
            intentData?.let { data ->
                val user = User(data.getStringExtra(NewUserActivity.EXTRA_REPLY), data.getStringExtra("GENDER"))
                userViewModel.insert(user)
                Unit
            }
        } else {
            Toast.makeText(
                applicationContext,
                "Uzupełnij nazwę lub wybierz płeć",
                Toast.LENGTH_LONG
            ).show()
        }
    }

}
