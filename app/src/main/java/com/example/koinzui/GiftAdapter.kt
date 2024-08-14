package com.example.koinzui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.koinzui.model.Gift

class GiftAdapter(private val gifts: List<Gift>) :
    RecyclerView.Adapter<GiftAdapter.GiftViewHolder>() {


    class GiftViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.gift_food_icon)
        val title: TextView = view.findViewById(R.id.gift_food_title)
        val points: TextView = view.findViewById(R.id.gift_food_points)
        val addButton: AppCompatButton = view.findViewById(R.id.add_button)
        val unlockRequirement: TextView = view.findViewById(R.id.gift_unlock_condition)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiftViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gift, parent, false)
        return GiftViewHolder(view)
    }

    override fun getItemCount(): Int = gifts.size


    override fun onBindViewHolder(
        holder: GiftViewHolder,
        position: Int,
    ) {
        if (gifts[position].isLocked){
            holder.image.setImageResource(gifts[position].lockedImage)
            holder.addButton.visibility = View.GONE
        }
         else
            holder.image.setImageResource(gifts[position].unlockedImage)
        if(gifts[position].condition.isBlank()){
            holder.unlockRequirement.visibility = View.GONE
        }
        holder.unlockRequirement.text = gifts[position].condition
        holder.title.text = gifts[position].title
        holder.points.text = "${gifts[position].points} pts"

        holder.addButton.setOnClickListener {
            // Define button click behavior here
        }
    }

}