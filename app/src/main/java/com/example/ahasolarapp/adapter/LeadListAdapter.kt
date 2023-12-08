package com.example.ahasolarapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.ahasolarapp.R
import com.example.ahasolarapp.model.LeadModel

interface OnItemClickListener {
    fun onEditClick(position: Int)
    fun onFilterClick(position: Int)
    fun onDeleteClick(position: Int)
}

class LeadListAdapter(private val context: Context, private val list: List<LeadModel>) :
    RecyclerView.Adapter<LeadListAdapter.LeadViewHolder>() {
    class LeadViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val popupMenu: ImageView = itemView.findViewById(R.id.popupMenu)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeadViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lead_item, parent, false)
        return LeadViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: LeadViewHolder, position: Int) {
        holder.popupMenu.setOnClickListener { view ->
            val popupMenu = PopupMenu(view.context, view)
            val inflater: MenuInflater = popupMenu.menuInflater
            inflater.inflate(R.menu.action_bar_menu, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.edit -> {
                        true
                    }

                    R.id.filter -> {
                        true
                    }

                    R.id.delete -> {
                        deleteItem(position)
                        true
                    }

                    else -> false
                }
            }

            popupMenu.show()
        }
    }

    private fun deleteItem(position: Int) {
//        list.removeAt(position)
        notifyItemRemoved(position)
    }
}

