package com.t24.peaceapp.ui.state

fun userLevelReducer(state: Any, action: Any) =
    when (action) {

        is UpdateUserLevel -> {
            println("userLevelReducer: ${action.level}")
            action.level
        }
        else -> state

    }