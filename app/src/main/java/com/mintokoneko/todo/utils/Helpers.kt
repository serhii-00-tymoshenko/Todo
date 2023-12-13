package com.mintokoneko.todo.utils

import android.content.res.Resources

fun pxToDp(px: Int): Float {
    return (px / Resources.getSystem().displayMetrics.density)
}

fun dpToPx(dp: Int): Int {
    return (dp * Resources.getSystem().displayMetrics.density).toInt()
}