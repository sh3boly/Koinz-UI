package com.example.koinzui

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.koinzui.model.Gift

class GiftAdapter(private val gifts: List<Gift>, private val activity: FragmentActivity) :
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
        val gift = gifts[position]
        if (gift.isLocked) {
            holder.image.setImageResource(gift.lockedImage)
            holder.addButton.visibility = View.GONE
        } else
            holder.image.setImageResource(gift.unlockedImage)
        if (gift.condition.isBlank()) {
            holder.unlockRequirement.visibility = View.GONE
        }
        holder.unlockRequirement.text = gift.condition
        holder.title.text = gift.title
        holder.points.text = "${gift.points} pts"

        holder.addButton.setOnClickListener {
            holder.addButton.setOnClickListener {
                val dialog = AddDialog(gift)
                dialog.show(activity.supportFragmentManager, "AddDialog")
            }
        }
    }

    private fun showPopupWindow(view: View, item: Gift) {
        val inflater = LayoutInflater.from(view.context)
        val popupView = inflater.inflate(R.layout.popup_window, null)

        val popupWindow = PopupWindow(
            popupView,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true
        )

        val title = popupView.findViewById<TextView>(R.id.gift_title)
        title.text = item.title

        val cancelButton = popupView.findViewById<ImageButton>(R.id.exit_btn)
        cancelButton.setOnClickListener {
            popupWindow.dismiss()
        }

        popupWindow.showAtLocation(view.rootView, Gravity.CENTER, 0, 0)
    }

}