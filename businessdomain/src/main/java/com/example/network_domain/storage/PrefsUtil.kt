package com.example.network_domain.storage

import android.content.SharedPreferences
import androidx.core.content.edit

class PrefsUtil(
    private val pref: SharedPreferences
) {

    companion object {
        const val SHARED_PREFERENCE_ID = "CENTER_PATIENT_SPF"
        private const val KEY_DARK_MODE = "KEY_DARK_MODE"
        private const val KEY_ACCESS_TOKEN = "KEY_ACCESS_TOKEN"

    }

    var darkMode: Boolean
        get() = pref.getBoolean(KEY_DARK_MODE, false)
        set(value) = pref.edit()
            .putBoolean(KEY_DARK_MODE, value)
            .apply()

    var accessToken: String?
        get() = pref.getString(KEY_ACCESS_TOKEN, null)
        set(value) = pref.edit { putString(KEY_ACCESS_TOKEN, value) }
}