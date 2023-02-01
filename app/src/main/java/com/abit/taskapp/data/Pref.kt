package com.abit.taskapp.data

import android.content.Context
import android.content.Context.MODE_PRIVATE

class Pref(private val context: Context) {

    companion object {
        const val PREF_NAME = "Task.pref"
        const val SEEN_KEY = "seen.key"
        const val PROF_KEY = "profile.key"
        const val IMAGE_KEY = "image.key"
        const val AGE_KEY = "age.key"
    }


    private val pref = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)

    fun isUserSeen(): Boolean {
        return pref.getBoolean(SEEN_KEY, false)
    }

    fun saveSeen() {
        pref.edit().putBoolean(SEEN_KEY, true).apply()
    }

    fun saveName(name: String) {
        pref.edit().putString(PROF_KEY, name).apply()
    }

    fun getName(): String {
        return pref.getString(PROF_KEY, "").toString()
    }

    fun saveImage(image: String) {
        pref.edit().putString(IMAGE_KEY, image).apply()
    }

    fun getImage(): String {
        return pref.getString(IMAGE_KEY, "").toString()
    }

    fun setAge(age: String) {
        pref.edit().putString(AGE_KEY, age).apply()
    }

    fun getAge(): String {
        return pref.getString(AGE_KEY, "").toString()
    }
}