package com.example.module_3_lesson_5_hw_3_compose

import android.content.Context

class SharedPrefs(context: Context) {

    private val RECORDSPRINT_1 = "record_sprint_1"
    private val RECORDSPRINT_2 = "record_sprint_2"
    private val RECORDSPRINT_3 = "record_sprint_3"
    private val RECORDSPRINT_4 = "record_sprint_4"
    private val RECORDSPRINT_5 = "record_sprint_5"

    private val RECORDMEDIUM_1 = "record_medium_1"
    private val RECORDMEDIUM_2 = "record_medium_2"
    private val RECORDMEDIUM_3 = "record_medium_3"
    private val RECORDMEDIUM_4 = "record_medium_4"
    private val RECORDMEDIUM_5 = "record_medium_5"

    private val RECORDMARATHON_1 = "record_marathon_1"
    private val RECORDMARATHON_2 = "record_marathon_2"
    private val RECORDMARATHON_3 = "record_marathon_3"
    private val RECORDMARATHON_4 = "record_marathon_4"
    private val RECORDMARATHON_5 = "record_marathon_5"

    private val prefs = context.getSharedPreferences("app_settings", Context.MODE_PRIVATE)

    // sprint
    fun setRecordSprintOne(record: Int) {
        prefs.edit().putInt(RECORDSPRINT_1, record).apply()
    }
    fun getRecordSprintOne(): Int {
        return prefs.getInt(RECORDSPRINT_1, 0)
    }
    fun setRecordSprintTwo(record: Int) {
        prefs.edit().putInt(RECORDSPRINT_2, record).apply()
    }
    fun getRecordSprintTwo(): Int {
        return prefs.getInt(RECORDSPRINT_2, 0)
    }
    fun setRecordSprintThree(record: Int) {
        prefs.edit().putInt(RECORDSPRINT_3, record).apply()
    }
    fun getRecordSprintThree(): Int {
        return prefs.getInt(RECORDSPRINT_3, 0)
    }
    fun setRecordSprintFour(record: Int) {
        prefs.edit().putInt(RECORDSPRINT_4, record).apply()
    }
    fun getRecordSprintFour(): Int {
        return prefs.getInt(RECORDSPRINT_4, 0)
    }
    fun setRecordSprintFive(record: Int) {
        prefs.edit().putInt(RECORDSPRINT_5, record).apply()
    }
    fun getRecordSprintFive(): Int {
        return prefs.getInt(RECORDSPRINT_5, 0)
    }

    // medium
    fun setRecordMediumOne(record: Int) {
        prefs.edit().putInt(RECORDMEDIUM_1, record).apply()
    }
    fun getRecordMediumOne(): Int {
        return prefs.getInt(RECORDMEDIUM_1, 0)
    }
    fun setRecordMediumTwo(record: Int) {
        prefs.edit().putInt(RECORDMEDIUM_2, record).apply()
    }
    fun getRecordMediumTwo(): Int {
        return prefs.getInt(RECORDMEDIUM_2, 0)
    }
    fun setRecordMediumThree(record: Int) {
        prefs.edit().putInt(RECORDMEDIUM_3, record).apply()
    }
    fun getRecordMediumThree(): Int {
        return prefs.getInt(RECORDMEDIUM_3, 0)
    }
    fun setRecordMediumFour(record: Int) {
        prefs.edit().putInt(RECORDMEDIUM_4, record).apply()
    }
    fun getRecordMediumFour(): Int {
        return prefs.getInt(RECORDMEDIUM_4, 0)
    }
    fun setRecordMediumFive(record: Int) {
        prefs.edit().putInt(RECORDMEDIUM_5, record).apply()
    }
    fun getRecordMediumFive(): Int {
        return prefs.getInt(RECORDMEDIUM_5, 0)
    }

    // marathon
    fun setRecordMarathonOne(record: Int) {
        prefs.edit().putInt(RECORDMARATHON_1, record).apply()
    }
    fun getRecordMarathonOne(): Int {
        return prefs.getInt(RECORDMARATHON_1, 0)
    }
    fun setRecordMarathonTwo(record: Int) {
        prefs.edit().putInt(RECORDMARATHON_2, record).apply()
    }
    fun getRecordMarathonTwo(): Int {
        return prefs.getInt(RECORDMARATHON_2, 0)
    }
    fun setRecordMarathonThree(record: Int) {
        prefs.edit().putInt(RECORDMARATHON_3, record).apply()
    }
    fun getRecordMarathonThree(): Int {
        return prefs.getInt(RECORDMARATHON_3, 0)
    }
    fun setRecordMarathonFour(record: Int) {
        prefs.edit().putInt(RECORDMARATHON_4, record).apply()
    }
    fun getRecordMarathonFour(): Int {
        return prefs.getInt(RECORDMARATHON_4, 0)
    }
    fun setRecordMarathonFive(record: Int) {
        prefs.edit().putInt(RECORDMARATHON_5, record).apply()
    }
    fun getRecordMarathonFive(): Int {
        return prefs.getInt(RECORDMARATHON_5, 0)
    }
}