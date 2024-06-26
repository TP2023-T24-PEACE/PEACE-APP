package com.t24.peaceapp.ui.state

data class UpdateUserId(val userId: String)
data class UpdateUserEmail(val email: String)
data class UpdateUserXP(val xp: Int)
data class UpdateUserLevel(val level: Int)
data class UpdateUserToken(val token: String)

data class UpdateChallenge(val challenge: String)

data class UpdatePriority(val priority: String)

data class UpdateSlider(val value: Float)
data class UpdateMoods(val moods: MutableList<Any>)
data class UpdateQuestions(val questions: List<String>)

data class UpdateAnswers(val answers: Pair<Any, Boolean>)
data class UpdateReasons(val reasons: MutableList<Any>)

