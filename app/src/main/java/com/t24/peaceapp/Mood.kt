package com.t24.peaceapp

import androidx.annotation.DrawableRes

data class Mood(
    val title: String,
    @DrawableRes val iconId: Int,
    val moodValue: Int,
    val selected: Boolean
)