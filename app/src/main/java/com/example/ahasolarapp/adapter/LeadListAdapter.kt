package com.example.ahasolarapp.adapter

import LeadViewModel
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

class LeadListAdapter(private val context: Context, private val list: List<LeadModel>,private val leadViewsModel: LeadViewModel) :
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
                        // Get the leadId of the clicked item
                        val leadId = list[position].leadId

                        // Call deleteLead function in the ViewModel
                        val authToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL3N0YWdpbmctYWhhc29sYXItcmV3YW1wLmFoYXNvbGFyLmluL2FwaS9hdXRoL2xvZ2luIiwiaWF0IjoxNzAxOTQ1NDY4LCJleHAiOjE3MDIxMzc0NjgsIm5iZiI6MTcwMTk0NTQ2OCwianRpIjoiUG9lV2U0ZVBSdElXS1Q3TCIsInN1YiI6IjIiLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3In0.N73t5AvksTXJYV_FL0s3OIVQf27lq5JmwFIEquNg9sw"
                        leadViewsModel.deleteLead(authToken, leadId)

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

