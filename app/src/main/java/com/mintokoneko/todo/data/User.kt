package com.mintokoneko.todo.data

import android.net.Uri

data class User(
    val name: String,
    val photoUri: Uri? = null
)
