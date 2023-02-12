package com.handsshot.foronefortwo.locale

import android.content.Context
import android.os.Build
import java.util.*

object LocaleHelper {
    private const val SELECTED_LANGUAGE = "Locale.Helper.Selected.Language"
    var LANGUAGE_CHANGED = false
    fun setLocale(context: Context, language: String) {
        persist(context, language)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            updateResources(context, language)
            return
        }
        updateResourcesLegacy(context, language)
    }

    fun updateLocale(context: Context): Context {
        val preferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
        persist(context, preferences.getString(SELECTED_LANGUAGE, "en")!!)
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            updateResources(context, preferences.getString(SELECTED_LANGUAGE, "en")!!)
        } else updateResourcesLegacy(context, preferences.getString(SELECTED_LANGUAGE, "en")!!)
    }

    fun getLocale(context: Context): String {
        val resources = context.resources
        val configuration = resources.configuration
        return configuration.locale.language
    }

    private fun persist(context: Context, language: String) {
        val preferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString(SELECTED_LANGUAGE, language)
        editor.apply()
    }

    private fun updateResources(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val configuration = context.resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)
        return context.createConfigurationContext(configuration)
    }

    private fun updateResourcesLegacy(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val resources = context.resources
        val configuration = resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)
        return context
    }
}