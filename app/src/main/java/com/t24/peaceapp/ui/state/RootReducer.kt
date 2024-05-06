package com.t24.peaceapp.ui.state

fun rootReducer(state: AppState, action: Any) = AppState(

//    todos = todosReducer(state.todos, action),
//    visibilityFilter = visibilityFilterReducer(state.visibilityFilter, action)

    userId = userIdReducer(state.userId, action),
    token = tokenReducer(state.token, action),
    value = sliderReducer(state.value, action),
    moods = moodsReducer(state.moods, action),
    reasons = reasonsReducer(state.reasons, action),
    questions = questionsReducer(state.questions, action),
    answers = answersReducer(state.answers, action)
)