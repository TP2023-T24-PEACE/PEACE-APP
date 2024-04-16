package com.t24.peaceapp.ui.state

fun rootReducer(state: AppState, action: Any) = AppState(

//    todos = todosReducer(state.todos, action),
//    visibilityFilter = visibilityFilterReducer(state.visibilityFilter, action)

    userId = userIdReducer(state.userId, action),
//    moods = moodsReducer(state.moods, action)
)