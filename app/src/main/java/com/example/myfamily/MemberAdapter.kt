package com.example.myfamily

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myfamily.databinding.ItemMemberBinding

class MemberAdapter(private val listMembers: List<MemberModel>) :
    RecyclerView.Adapter<MemberAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val item = ItemMemberBinding.inflate(inflater, parent, false)
        return ViewHolder(item)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listMembers[position]
        holder.name.text = item.name
        holder.address.text = item.address
        holder.battrey.text = item.battrey
        holder.distance.text = item.distance
        holder.image_one.setImageDrawable(ContextCompat.getDrawable(holder.image_one.context, item.image) )

        holder.call.setOnClickListener {
            openCall(holder.call)
        }

    }

    override fun getItemCount(): Int {
        return listMembers.size
    }



    private fun openCall(call: ImageView) {
        val callIntent = Intent(Intent.ACTION_CALL);
        callIntent.setData((Uri.parse("tel:" + 7014822805)));
        call.context.startActivity(callIntent);
    }


    class ViewHolder(private val item: ItemMemberBinding) : RecyclerView.ViewHolder(item.root) {

        val image_one = item.oneImg
        val name = item.cardoneText
        val address = item.msg
        val battrey = item.batteryPercent
        val distance = item.distanceValue
        val call = item.call


    }

}


