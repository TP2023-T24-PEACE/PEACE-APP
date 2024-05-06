package com.t24.peaceapp.ui.state

import android.content.Context
import android.os.StrictMode
import khttp.post

fun postAnswer(reason: Pair<Any, Any>) {

    val sharedPref = context.getSharedPreferences("userId", Context.MODE_PRIVATE)
    val loggedInUserId = sharedPref.getString("userId", "")
    val sharedPrefToken = context.getSharedPreferences("token", Context.MODE_PRIVATE)
    val token = sharedPrefToken.getString("token", "")

    println("user_id: $loggedInUserId")


    val authorization = "Bearer"+ (token?.replace("\"", "") ?: "")

    val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
    StrictMode.setThreadPolicy(policy)

    val body = """
        {
            "type": "sliding_game",
            "user_id":$loggedInUserId,
            "questions": {"${reason.first}" : ${reason.second}}
        }
    """.trimIndent()

    println(body)

    // Send async POST request to server
    val headers = mapOf(
        "Content-Type" to "application/json",
        "X-Apikey" to "30fa4be8-f8bb-4131-80bb-eda62eb9d116",
        "Authorization" to authorization

    )
    val response =  post("https://tp-be-production.up.railway.app/api/v1/entries",
        headers = headers,
        data = body)


    println(response)
}
fun answersReducer(state: Any, action: Any) =
    when (action) {

        is UpdateAnswers -> {
            println("answersReducer: ${action.answers}")
            postAnswer(action.answers)
            action.answers
        }
        else -> state

    }