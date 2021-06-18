package com.akshatsahijpal.socialgraph.util

import androidx.lifecycle.Observer

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
) : Observer<Event<Resource<T>>> {
    override fun onChanged(t: Event<Resource<T>>?) {
        when (val content: Resource<T>? = t?.peek()) {
            is Resource.Success -> {
                content.data?.let(onSuccess)
            }
            is Resource.Fail -> {
                t.getContentIfNotHandled()?.let {
                    onError?.let { error ->
                        error(it.message!!)
                    }
                }
            }
            is Resource.Loading -> {
                onLoading?.let { load ->
                    load()
                }
            }
        }
    }
}