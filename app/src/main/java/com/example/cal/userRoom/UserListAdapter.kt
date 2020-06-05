package com.example.cal.userRoom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cal.R
import kotlinx.android.synthetic.main.activity_start.view.*
import kotlinx.android.synthetic.main.record_view_holder.view.*
import kotlinx.android.synthetic.main.user_selection_view_holder.view.*

class UserListAdapter internal constructor(
    context: Context,
    val clickListener: (User) -> Unit
): RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var users = emptyList<User>()

    inner class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val userItemView: TextView = itemView.findViewById(R.id.rv_textView)
        val userGenderItemView: TextView = itemView.findViewById(R.id.rvg_textView)

        fun bind(user: User, clickListener: (User) -> Unit) {
            itemView.rv_textView.text = user.userName
            itemView.rvg_textView.text = user.gender
            itemView.setOnClickListener { clickListener(user) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = inflater.inflate(R.layout.user_selection_view_holder, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val current = users[position]
        holder.userItemView.text = current.userName
        holder.userGenderItemView.text = current.gender
        holder.bind(current, clickListener )
    }

    internal fun setWords(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

    override fun getItemCount() = users.size
}
