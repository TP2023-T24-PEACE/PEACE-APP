package com.t24.peaceapp

data class UpdateUserId(val userId: String)
data class AddTodo(val text: String, val completed: Boolean = false)
data class ToggleTodo(val index: Int)
data class SetVisibilityFilter(val visibilityFilter: VisibilityFilter)

