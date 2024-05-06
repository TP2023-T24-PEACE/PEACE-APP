package com.t24.peaceapp.ui.state

fun userXpReducer(state: Any, action: Any) =
    when (action) {

        is UpdateUserXP -> {
            println("userXpReducer: ${action.xp}")
            action.xp
        }
        else -> state

    }