package com.example.cal

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cal.historyRoom.HistoryListAdapter
import com.example.cal.historyRoom.HistoryViewModel
class HistoryCheckActivity : AppCompatActivity() {

    private lateinit var historyViewModel: HistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_check)

        val Intent = getIntent()
        val userName = Intent.getStringExtra("userName")
        val userGender = Intent.getStringExtra("gender")

      //  var historyViewModel = HistoryViewModel.HistoryViewModelFactory(application, userName)

        val historyRecyclerView = findViewById<RecyclerView>(R.id.history_recyclerView)
        val adapter = HistoryListAdapter(this)
        historyRecyclerView.adapter = adapter
        historyRecyclerView.layoutManager = LinearLayoutManager(this)




        historyViewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)
        historyViewModel.userName = userName
        historyViewModel = ViewModelProvider(this).get(historyViewModel.)
        historyViewModel.history.observe(this, Observer { records ->
            records?.let { adapter.setWords(it) }

        })
    }

}
