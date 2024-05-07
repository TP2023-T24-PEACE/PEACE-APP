package com.t24.peaceapp.ui.state


fun questionsReducer(state: List<Any>, action: Any) =
    when (action) {

        is UpdateQuestions -> {
            println("questionsReducer: ${action.questions}")
            action.questions
        }
        else -> state

    }