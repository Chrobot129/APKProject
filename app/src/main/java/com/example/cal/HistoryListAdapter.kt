package com.example.cal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
// TO DO
//class HistoryListAdapter internal constructor(
//    context: Context
//) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {
//
//    private val inflater: LayoutInflater = LayoutInflater.from(context)
//    private var words = emptyList<History>() // Cached copy of words
//
//    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val wordItemView: TextView = itemView.findViewById(R.id.textView)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
//        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
//        return WordViewHolder(itemView)
//    }
//
//    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
//        val current = words[position]
//        holder.wordItemView.text = current.history
//    }
//
//    internal fun setWords(words: List<History>) {
//        this.words = words
//        notifyDataSetChanged()
//    }
//
//    override fun getItemCount() = words.size
//}