package com.t24.peaceapp

fun visibilityFilterReducer(state: VisibilityFilter, action: Any) =
    when (action) {
        is SetVisibilityFilter -> action.visibilityFilter
        else -> state
    }