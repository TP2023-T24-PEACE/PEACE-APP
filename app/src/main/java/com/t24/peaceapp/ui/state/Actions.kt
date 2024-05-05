package com.t24.peaceapp.ui.state

data class UpdateUserId(val userId: String)
data class UpdateUserToken(val token: String)

data class addMoods(val data: String)
data class AddTodo(val text: String, val completed: Boolean = false)
data class ToggleTodo(val index: Int)
data class SetVisibilityFilter(val visibilityFilter: VisibilityFilter)

