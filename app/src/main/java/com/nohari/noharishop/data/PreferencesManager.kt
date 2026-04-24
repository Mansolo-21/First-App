package com.nohari.noharishop.data

import android.content.Context

fun isFirstTime(context: Context): Boolean {
    val prefs = context.getSharedPreferences("prefs", 0)
    return prefs.getBoolean("first_time", true)
}

fun setNotFirstTime(context: Context) {
    val prefs = context.getSharedPreferences("prefs", 0)
    prefs.edit().putBoolean("first_time", false).apply()
}