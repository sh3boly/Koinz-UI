package com.example.koinzui

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupWindow
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.koinzui.databinding.ItemBottomsheetBinding
import com.example.koinzui.model.Gift
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalBottomSheetDialog : BottomSheetDialogFragment() {
    private lateinit var binding: ItemBottomsheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ItemBottomsheetBinding.inflate(inflater, container, false)
        setupRecyclerView()
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), R.style.CustomBottomSheetDialogTheme)

        dialog.setOnShowListener { it ->
            val d = it as BottomSheetDialog
            val bottomSheet = d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)

            bottomSheet?.let {
                val behavior = BottomSheetBehavior.from(it)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }

        return dialog
    }

    companion object {
        const val TAG = "ModalBottomSheetDialog"
    }

    private fun setupRecyclerView() {
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        activity?.let { fragmentActivity ->
            recyclerView.adapter = GiftAdapter(getGiftList(), fragmentActivity)
        }
    }

    private fun getGiftList(): List<Gift> {
        val gifts = mutableListOf<Gift>()
        gifts.add(Gift(unlockedImage = R.drawable.ic_voucher, title = "30% Discount Voucher", points = 45))
        gifts.add(
            Gift(
                unlockedImage = R.drawable.ic_regular_burger,
                title = "Regular Burger",
                points = 90,
                condition = "Spend 30 L.E to unlock this gift for next visit",
                isLocked = false
            )
        )
        gifts.add(
            Gift(
                lockedImage = R.drawable.ic_locked_burger,
                title = "Cheese Burger",
                points = 90,
                condition = "Spend 30 L.E to unlock this gift for next visit",
                isLocked = true
            )
        )
        gifts.add(
            Gift(
                lockedImage = R.drawable.ic_classic_burger,
                title = "Classic Burger",
                points = 120,
                condition = "Spend 30 L.E to unlock this gift for next visit",
                isLocked = true
            )
        )
        return gifts
    }



}