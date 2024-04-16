package com.t24.peaceapp

fun userIdReducer(state: String, action: Any) =
    when (action) {
        is UpdateUserId -> action.userId
        else -> state
    }