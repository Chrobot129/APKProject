package com.example.cal.historyRoom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cal.R
import kotlinx.android.synthetic.main.activity_start.view.*

class HistoryListAdapter internal constructor(
    context: Context,
    val userName: String
): RecyclerView.Adapter<HistoryListAdapter.HistoryViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var history = emptyList<Record>()

    inner class HistoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val bmiItemView: TextView = itemView.findViewById(R.id.bmi_textView)
        val timeItemView: TextView = itemView.findViewById(R.id.time_textView)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val itemView = inflater.inflate(R.layout.record_view_holder, parent, false)
        return HistoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val current = history[position]
            holder.bmiItemView.text = current.bmr
            holder.timeItemView.text = current.time

    }

    internal fun setWords(history: List<Record>) {
        this.history = history.filter { record: Record ->  record.userName.equals(userName)}
        notifyDataSetChanged()
    }

    override fun getItemCount() = history.size
}
