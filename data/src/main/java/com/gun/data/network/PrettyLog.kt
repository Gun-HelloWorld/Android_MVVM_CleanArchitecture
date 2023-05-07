package com.gun.data.network

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.gun.domain.common.Constants.TAG
import okhttp3.logging.HttpLoggingInterceptor

internal class PrettyLogger : HttpLoggingInterceptor.Logger {
    private val mGson = GsonBuilder().setPrettyPrinting().create()

    override fun log(message: String) {
        val trimMessage = message.trim { it <= ' ' }
        if (trimMessage.startsWith("{") && trimMessage.endsWith("}")
            || trimMessage.startsWith("[") && trimMessage.endsWith("]")
        ) {
            try {
                val prettyJson = mGson.toJson(JsonParser.parseString(message))
                Log.d(TAG, prettyJson, null)
            } catch (e: Exception) {
                Log.d(TAG, message, e)
            }
        } else {
            Log.d(TAG, message, null)
        }
    }
}