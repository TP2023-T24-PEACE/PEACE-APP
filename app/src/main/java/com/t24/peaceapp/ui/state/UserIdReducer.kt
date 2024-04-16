package com.t24.peaceapp.ui.state

import android.content.Context
import com.t24.peaceapp.ui.screens.MainActivity

var context = MainActivity.context

fun userIdReducer(state: Any, action: Any) =
    when (action) {

        is UpdateUserId -> {
            action.userId
            // put userId into shared preferences
            val sharedPref = context.getSharedPreferences("userId", Context.MODE_PRIVATE)
            with (sharedPref.edit()) {
                putString("userId", action.userId)
                apply()
            }

            // Print the userId from shared preferences
            val userId = sharedPref.getString("userId", "")
            println("userId from sharedPref: $userId")
        }
        else -> state

    }