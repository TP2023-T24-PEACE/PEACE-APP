package com.t24.peaceapp.ui.state

fun moodReducer(state: Any, action: Any) =
    when (action) {

        is addMoods -> {
            if(action.data != null) {
                action.data
                println("moods from moodReducer: $action.data")
            }
            else {
                action.data
                println("moods from moodReducer: null")
            }
        }
        else -> state

    }