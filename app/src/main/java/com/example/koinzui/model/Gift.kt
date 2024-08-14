package com.example.koinzui.model

import android.graphics.drawable.Drawable

data class Gift(
    val lockedImage: Int = -1,
    val unlockedImage: Int = -1,
    val condition: String = "",
    val title: String,
    val points: Int,
    val isLocked: Boolean = false,

)
