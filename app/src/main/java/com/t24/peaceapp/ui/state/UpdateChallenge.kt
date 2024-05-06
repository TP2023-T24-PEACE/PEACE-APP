package com.t24.peaceapp.ui.state

fun userChallengeReducer(state: String, action: Any) =
    when (action) {

        is UpdateChallenge -> {
            println("userChallengeReducer: ${action.challenge}")
            action.challenge
        }
        else -> state

    }