package com.akshatsahijpal.socialgraph.util
// class gives status of data being unloaded from remote source
    sealed class Resource<T>(var data: T? = null, var message: String? = null) {
    class Success<T>(data: T)// if Successful, called with data
        : Resource<T>(data)
    class Fail<T>(data: T? = null, message: String)// if Not Successful, called with error message
        : Resource<T>(data, message = message)
    class Loading<T>(data: T? = null) : Resource<T>(data)// loading state
}