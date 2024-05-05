package com.t24.peaceapp.ui.state

import android.content.Context

fun tokenReducer(state: Any, action: Any) =
    when (action) {

        is UpdateUserToken -> {
            action.token
            // put userId into shared preferences
            val sharedPrefToken = context.getSharedPreferences("token", Context.MODE_PRIVATE)
            with (sharedPrefToken.edit()) {
                putString("token", action.token)
                apply()
            }

            // Print the userId from shared preferences
            val token = sharedPrefToken.getString("token", "")
            println("token from sharedPref: $token")
        }
        else -> state

    }