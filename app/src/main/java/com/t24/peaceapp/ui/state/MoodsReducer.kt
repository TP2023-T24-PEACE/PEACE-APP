package com.t24.peaceapp.ui.state


fun moodsReducer(state: List<Any>, action: Any) =
    when (action) {

        is UpdateMoods -> {
            println("moodsReducer: ${action.moods}")
            action.moods
        }
        else -> state

    }