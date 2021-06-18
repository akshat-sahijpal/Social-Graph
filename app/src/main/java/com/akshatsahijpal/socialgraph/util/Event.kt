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

class EventObserver<T>(
    private inline val onError: ((err: String) -> Unit)? = null,
    private inline val onSuccess: ((T) -> Unit),
    private inline val onLoading: (() -> Unit)? = null,
) {

}