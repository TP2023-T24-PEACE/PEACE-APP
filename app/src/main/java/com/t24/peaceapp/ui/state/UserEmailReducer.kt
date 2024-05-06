package com.t24.peaceapp.ui.state

fun userEmailReducer(state: Any, action: Any) =
    when (action) {

        is UpdateUserEmail -> {
            println("userEmailReducer: ${action.email}")
            action.email
        }
        else -> state

    }