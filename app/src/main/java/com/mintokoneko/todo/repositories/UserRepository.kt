package com.mintokoneko.todo.repositories

import android.content.Context
import com.mintokoneko.todo.base.BaseRepository
import com.mintokoneko.todo.data.User

class UserRepository(context: Context) : BaseRepository(context) {

    fun getUserDetails(): User = sharedPreferences.getUserDetails()

    fun putUserDetails(user: User) = sharedPreferences.putUserDetails(user)

    fun setUserDetailsListener(listener: () -> Unit) {
        sharedPreferences.setUserDetailsListener(listener)
    }

    companion object {
        private var instance: UserRepository? = null

        fun getInstance(context: Context) =
            instance ?: UserRepository(context).also { instance = it }
    }
}