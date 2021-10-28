package com.example.myfamily

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myfamily.databinding.ItemInviteMailBinding

class InviteMailAdapter: RecyclerView.Adapter<InviteMailAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InviteMailAdapter.ViewHolder {
       val inflater  = LayoutInflater.from(parent.context)
        val item = ItemInviteMailBinding.inflate(inflater,parent,false)
       return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: InviteMailAdapter.ViewHolder, position: Int) {
       val item  = listInvite[position]
        holder.name.text = item
    }

    override fun getItemCount(): Int {

        return listInvites.size
    }


    class ViewHolder(private val item:ItemInviteMailBinding):RecyclerView.ViewHolder(item.root) {

    }
}