package com.example.myfamily

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.myfamily.databinding.ItemInviteBinding

class InviteAdapter(private val listContacts: List<ContactModel>) :
    RecyclerView.Adapter<InviteAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val Inflater = LayoutInflater.from(parent.context)

        val item = ItemInviteBinding.inflate(Inflater, parent, false)
        return ViewHolder(item)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listContacts[position]
        holder.name.text = item.name
    }

    override fun getItemCount(): Int {
        return listContacts.size
    }

    class ViewHolder(private val item:  ItemInviteBinding) : RecyclerView.ViewHolder(item.root) {
        val name = item.userText


    }
}