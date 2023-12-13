package com.mintokoneko.todo.repositories.shared_preferences

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import androidx.core.net.toUri
import com.mintokoneko.todo.data.User
import com.mintokoneko.todo.utils.SHARED_PREFERENCES_NAME
import com.mintokoneko.todo.utils.USER_NAME_PREFERENCE_NAME
import com.mintokoneko.todo.utils.USER_PHOTO_URI_PREFERENCE_NAME

class AppSharedPreferences(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    companion object {
        @Volatile
        private var instance: AppSharedPreferences? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: AppSharedPreferences(context).also { instance = it }
            }
    }

    fun putUserDetails(user: User) {
        putUserName(user.name)
        putUserPhotoUri(user.photoUri!!)
    }

    private fun putUserPhotoUri(uri: Uri) {
        sharedPreferences
            .edit()
            .putString(USER_PHOTO_URI_PREFERENCE_NAME, uri.toString())
            .apply()
    }

    private fun putUserName(name: String) {
        sharedPreferences
            .edit()
            .putString(USER_NAME_PREFERENCE_NAME, name)
            .apply()
    }

    fun getUserDetails(): User {
        val name = getUserName()
        val photoUri = getUserPhotoUri()

        return User(name, photoUri)
    }

    private fun getUserPhotoUri(): Uri {
        val photoUri = sharedPreferences.getString(USER_PHOTO_URI_PREFERENCE_NAME, "")!!.toUri()
        return photoUri
    }

    private fun getUserName(): String {
        val name = sharedPreferences.getString(USER_NAME_PREFERENCE_NAME, "Profile")!!
        return name
    }

    fun setUserDetailsListener(listener: () -> Unit) {
        val preferencesListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            if (key == USER_PHOTO_URI_PREFERENCE_NAME || key == USER_NAME_PREFERENCE_NAME) {
                listener.invoke()
            }
        }

        sharedPreferences.registerOnSharedPreferenceChangeListener(preferencesListener)
    }
}