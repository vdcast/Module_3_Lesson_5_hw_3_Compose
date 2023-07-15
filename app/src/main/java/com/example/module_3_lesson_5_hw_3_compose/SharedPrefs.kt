package com.example.module_3_lesson_5_hw_3_compose

import android.content.Context

class SharedPrefs(context: Context) {
    private val RECORDSPRINT = "record sprint"
    private val prefs = context.getSharedPreferences("app_settings", Context.MODE_PRIVATE)

    fun setRecordSprint(record: Int) {
        prefs.edit().putInt(RECORDSPRINT, record).apply()
    }
    fun getRecordSprint(): Int {
        return prefs.getInt(RECORDSPRINT, 0)
    }

}