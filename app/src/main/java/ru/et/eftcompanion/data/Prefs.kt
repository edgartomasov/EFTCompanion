package ru.et.eftcompanion.data

import android.content.SharedPreferences
import javax.inject.Inject

class Prefs
@Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun put(name: String, value: String) {
        sharedPreferences.edit().putString(name, value).apply()
    }

    fun put(name: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(name, value).apply()
    }

    fun put(name: String, value: Long) {
        sharedPreferences.edit().putLong(name, value).apply()
    }

    fun getString(name: String): String? {
        return sharedPreferences.getString(name, null)
    }

    fun getLong(name: String): Long {
        return sharedPreferences.getLong(name, 0)
    }

    fun getBoolean(name: String): Boolean {
        return sharedPreferences.getBoolean(name, false)
    }
}