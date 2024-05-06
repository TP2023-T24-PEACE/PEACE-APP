package com.t24.peaceapp.ui.state

fun userPriorityReducer(state: String, action: Any) =
    when (action) {

        is UpdatePriority -> {
            println("userPriorityReducer: ${action.priority}")
            action.priority
        }
        else -> state

    }