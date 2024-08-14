package com.example.koinzui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        // Create a BottomSheetDialog with the custom theme
        val dialog = BottomSheetDialog(requireContext(), R.style.CustomBottomSheetDialogTheme)

        // Set up the BottomSheetDialog as usual
        dialog.setOnShowListener { it ->
            val d = it as BottomSheetDialog
            val bottomSheet = d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)

            bottomSheet?.let {
                val behavior = BottomSheetBehavior.from(it)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }

        return dialog // Return the customized dialog
    }

    companion object {
        const val TAG = "ModalBottomSheetDialog"
    }

    private fun setupRecyclerView() {
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter =
            GiftAdapter(getGiftList()) // Replace getGiftList() with your method to get the list of gifts
    }

    private fun getGiftList(): List<Gift> {
        val gifts = mutableListOf<Gift>()
        gifts.add(Gift(unlockedImage = R.drawable.ic_fries, title = "Fries", points = 140))
        gifts.add(
            Gift(
                lockedImage = R.drawable.ic_locked_burger,
                title = "Cheese Burger",
                points = 90,
                condition = "Spend 30 L.E to unlock this gift for next visit",
                isLocked = true
            )
        )
        return gifts
    }

}