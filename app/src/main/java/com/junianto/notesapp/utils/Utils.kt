package com.junianto.notesapp.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.text.SimpleDateFormat
import java.util.*

fun getCurrentDate(): String {
    val date = Date()
    val formatter = SimpleDateFormat("dd-MM-yyyy ', ' HH:mm", Locale.getDefault())

    return formatter.format(date)
}

fun hideSoftKeyboard(activity: Activity, view: View) {
    val inputMethodManager: InputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}