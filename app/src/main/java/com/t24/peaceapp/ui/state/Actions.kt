package com.t24.peaceapp.ui.state

import com.t24.peaceapp.Mood

data class UpdateUserId(val userId: String)
data class UpdateUserToken(val token: String)

data class UpdateSlider(val value: Float)
data class UpdateMoods(val moods: MutableList<Any>)


data class UpdateReasons(val reasons: List<Mood>)

