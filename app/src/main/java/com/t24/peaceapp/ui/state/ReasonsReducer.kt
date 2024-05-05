package com.t24.peaceapp.ui.state


fun reasonsReducer(state: List<Any>, action: Any) =
    when (action) {

        is UpdateReasons -> {
            println("reasonsReducer: ${action.reasons}")
            action.reasons
        }
        else -> state

    }