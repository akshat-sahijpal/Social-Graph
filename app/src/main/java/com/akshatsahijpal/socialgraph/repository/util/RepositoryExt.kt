package com.akshatsahijpal.socialgraph.repository.util

import android.util.Log
import com.akshatsahijpal.socialgraph.util.Resource

inline fun <T> safeCall(action: () -> Resource<T>): Resource<T> {
    return try {
        action()
    } catch (e: Exception) {
        Resource.Fail(message = e.message?:"Unknown Error")
    }
}