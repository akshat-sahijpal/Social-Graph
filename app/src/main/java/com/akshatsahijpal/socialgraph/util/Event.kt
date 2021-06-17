package com.akshatsahijpal.socialgraph.util

class Event<out T>(private var data: T) {
    var isHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        return if (!isHandled) {
            isHandled = true
            data
        } else null
    }
    fun peek() = data
}