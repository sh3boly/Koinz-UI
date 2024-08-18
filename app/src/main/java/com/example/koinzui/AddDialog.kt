package com.example.koinzui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.koinzui.model.Gift

class AddDialog(private val item: Gift) : DialogFragment() {
    override fun getTheme() = R.style.RoundedCornersDialog

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use a builder to create the dialog.
            val builder = AlertDialog.Builder(it)

            val inflater = requireActivity().layoutInflater

            val popupView = inflater.inflate(R.layout.popup_window, null)

            val image = popupView.findViewById<ImageView>(R.id.gift_icon)
            image.setImageResource(item.unlockedImage)

            val title = popupView.findViewById<TextView>(R.id.gift_title)
            if(item.title == "30% Discount Voucher") {
                title.text = "30% Discount"
            }else {
                title.text = item.title
            }
            val points = popupView.findViewById<TextView>(R.id.gift_points)
            points.text = "${item.points} points"

            val cancelButton = popupView.findViewById<ImageButton>(R.id.exit_btn)
            cancelButton.setOnClickListener {
                dismiss()
            }


            builder.setView(popupView)

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
