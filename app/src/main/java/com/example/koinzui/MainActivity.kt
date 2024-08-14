package com.example.koinzui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.example.koinzui.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupModalButtons()
    }

    private fun setupModalButtons() {
        binding.apply {
            btnModal.setOnClickListener {
                val modal = ModalBottomSheetDialog()
                supportFragmentManager.let { modal.show(it, ModalBottomSheetDialog.TAG) }
            }
        }
    }
}