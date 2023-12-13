package com.mintokoneko.todo.base

import android.content.Context
import com.mintokoneko.todo.repositories.shared_preferences.AppSharedPreferences
import kotlin.reflect.KClass


open class BaseRepository(context: Context) {
    protected val sharedPreferences by lazy {
        AppSharedPreferences.getInstance(context)
    }
}