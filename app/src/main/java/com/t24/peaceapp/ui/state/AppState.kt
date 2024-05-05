package com.t24.peaceapp.ui.state

data class AppState(
    val userId: Any = "",
    val token: Any = "",
    val value: Any = "",
    val moods: List<Any> = listOf(),
    val reasons: List<Any> = listOf(),
)

enum class VisibilityFilter {
    SHOW_ALL,
    SHOW_COMPLETED,
    SHOW_ACTIVE
}