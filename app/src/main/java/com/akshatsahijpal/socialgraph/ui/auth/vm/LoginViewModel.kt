package com.akshatsahijpal.socialgraph.ui.auth.vm

import android.content.Context
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akshatsahijpal.socialgraph.repository.auth.AuthRepoC
import com.akshatsahijpal.socialgraph.util.Constants.EMPTY_FIELD_ERROR
import com.akshatsahijpal.socialgraph.util.Constants.INVALID_EMAIL_FORMAT_ERROR
import com.akshatsahijpal.socialgraph.util.Constants.MAX_NAME_ERROR
import com.akshatsahijpal.socialgraph.util.Constants.MAX_PASSWORD_ERROR
import com.akshatsahijpal.socialgraph.util.Constants.MAX_PASSWORD_LENGTH
import com.akshatsahijpal.socialgraph.util.Constants.MAX_USERNAME_LENGTH
import com.akshatsahijpal.socialgraph.util.Constants.MIN_NAME_ERROR
import com.akshatsahijpal.socialgraph.util.Constants.MIN_PASSWORD_ERROR
import com.akshatsahijpal.socialgraph.util.Constants.MIN_PASSWORD_LENGTH
import com.akshatsahijpal.socialgraph.util.Constants.MIN_USERNAME_LENGTH
import com.akshatsahijpal.socialgraph.util.Event
import com.akshatsahijpal.socialgraph.util.Resource
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private var repo: AuthRepoC,
    context: Context,
    private var dispatcher: CoroutineDispatcher
) : ViewModel() {
    private var _observeRegisterStatus = MutableLiveData<Event<Resource<AuthResult>>>() // Mutable
    var observerRegister: LiveData<Event<Resource<AuthResult>>> =
        _observeRegisterStatus // ImMutable

    fun registerUser(username: String, email: String, password: String) {
        val error: String? =
            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) EMPTY_FIELD_ERROR
            else if (username.length < MIN_USERNAME_LENGTH) MIN_NAME_ERROR
            else if (username.length > MAX_USERNAME_LENGTH) MAX_NAME_ERROR
            else if (password.length < MIN_PASSWORD_LENGTH) MIN_PASSWORD_ERROR
            else if (password.length > MAX_PASSWORD_LENGTH) MAX_PASSWORD_ERROR
            else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) INVALID_EMAIL_FORMAT_ERROR
            else null
        if (error != null) {
            _observeRegisterStatus.postValue(Event(Resource.Fail(message = error)))
        }
        _observeRegisterStatus.postValue(Event(Resource.Loading()))
        viewModelScope.launch {
            val result : Resource<AuthResult> = repo.registerNewUser(userMail = email, userName = username, userPassword = password)
            _observeRegisterStatus.postValue(Event(result))
        }
    }
}