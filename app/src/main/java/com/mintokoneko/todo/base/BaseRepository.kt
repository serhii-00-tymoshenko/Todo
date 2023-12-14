package com.mintokoneko.todo.base

import android.content.Context
import com.mintokoneko.todo.repositories.sharedpreferences.AppSharedPreferences

open class BaseRepository(context: Context) {
    protected val sharedPreferences by lazy {
        AppSharedPreferences.getInstance(context)
    }
}