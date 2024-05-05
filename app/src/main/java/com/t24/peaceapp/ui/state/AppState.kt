package com.t24.peaceapp.ui.state

import com.t24.peaceapp.Mood

data class AppState(
    val userId: Any = "",
    val token: Any = "",
    val data: Any = ""
//    val reasons: List<Reason> = listOf(),


//    val todos: List<Todo> = listOf(),
//    val visibilityFilter: VisibilityFilter = VisibilityFilter.SHOW_ALL
) {
//    val visibleTodos: List<Todo>
//        get() = getVisibleTodos(visibilityFilter)
//
//    private fun getVisibleTodos(visibilityFilter: VisibilityFilter) = when (visibilityFilter) {
//        VisibilityFilter.SHOW_ALL -> todos
//        VisibilityFilter.SHOW_ACTIVE -> todos.filter { !it.completed }
//        VisibilityFilter.SHOW_COMPLETED -> todos.filter { it.completed }
//    }
}


data class Todo(
    val text: String,
    val completed: Boolean = false,
    val id: Int
)

enum class VisibilityFilter {
    SHOW_ALL,
    SHOW_COMPLETED,
    SHOW_ACTIVE
}