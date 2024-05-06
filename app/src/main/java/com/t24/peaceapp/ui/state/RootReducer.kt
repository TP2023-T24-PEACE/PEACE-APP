package com.t24.peaceapp.ui.state

fun rootReducer(state: AppState, action: Any) = AppState(

    userId = userIdReducer(state.userId, action),
    email = userEmailReducer(state.email, action),
    xp = userXpReducer(state.xp, action),
    level = userLevelReducer(state.level, action),
    token = tokenReducer(state.token, action),
    value = sliderReducer(state.value, action),
    moods = moodsReducer(state.moods, action),
    reasons = reasonsReducer(state.reasons, action),
    questions = questionsReducer(state.questions, action),
    answers = answersReducer(state.answers, action),
    challenge = userChallengeReducer(state.challenge, action),
    priority = userPriorityReducer(state.priority, action)
)