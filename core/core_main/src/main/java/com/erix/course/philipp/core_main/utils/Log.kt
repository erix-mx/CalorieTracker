package com.erix.course.philipp.core_main.utils

import android.util.Log

fun loge(tag: String = "err", message: () -> String) {
    Log.e(tag, message())
}