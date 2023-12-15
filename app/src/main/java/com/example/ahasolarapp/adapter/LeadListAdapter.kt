package com.example.ahasolarapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ahasolarapp.R
import com.example.ahasolarapp.databinding.LeadItemBinding
import com.example.ahasolarapp.model.LeadModel

interface OnItemClickListener {
    fun onDeleteClick(position: LeadModel)
}


class LeadListAdapter(private val context: Context, private var originalList: List<LeadModel>,private val onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<LeadListAdapter.LeadViewHolder>() {
    class LeadViewHolder( val binding: LeadItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: LeadModel) {

            val projectName = item.projectName
            val capitalizedProjectName = projectName.substring(0, 1).toUpperCase() + projectName.substring(1)
            item.projectName = capitalizedProjectName

            binding.model = item
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeadViewHolder {
        val binding: LeadItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.lead_item, parent, false)
        return LeadViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return originalList.size
    }

    override fun onBindViewHolder(holder: LeadViewHolder, position: Int) {
        holder.bind(originalList[position])
        holder.binding.name

        holder.binding.popupMenu.setOnClickListener { view ->
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

                        onItemClickListener.onDeleteClick(originalList[position])
                        //deleteItem(position)
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
    fun updateList(newList: List<LeadModel>) {
        originalList = newList
        notifyDataSetChanged()
    }
}

