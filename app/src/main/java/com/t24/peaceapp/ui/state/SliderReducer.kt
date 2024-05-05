package com.t24.peaceapp.ui.state

fun sliderReducer(state: Any, action: Any) =
    when (action) {

        is UpdateSlider -> {
                println("updateSliderReducer: ${action.value}")
                action.value
        }
        else -> state

    }