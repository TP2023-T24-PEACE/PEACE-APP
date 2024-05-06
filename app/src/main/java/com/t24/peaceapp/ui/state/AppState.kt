package com.t24.peaceapp.ui.state

data class AppState(
    val userId: Any = "",
    val token: Any = "",
    val value: Any = "",
    val moods: List<Any> = listOf(),
    val reasons: List<Any> = listOf(),
    val questions: List<Any> = listOf(),
    val answers: Any = "",
    val email: Any = ""
)