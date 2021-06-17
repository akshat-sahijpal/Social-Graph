package com.akshatsahijpal.socialgraph.ui.auth.vm

import android.content.Context
import androidx.lifecycle.ViewModel
import com.akshatsahijpal.socialgraph.repository.auth.AuthRepoC
import com.akshatsahijpal.socialgraph.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private var repo: AuthRepoC,
    context: Context,
    private var dispatcher: CoroutineDispatcher
) : ViewModel() {
}