package com.akshatsahijpal.socialgraph.repository.auth

import com.akshatsahijpal.socialgraph.util.Resource
import com.google.firebase.auth.AuthResult
import dagger.Provides

// Interface helps for testing
interface AuthRepository {
    suspend fun registerNewUser(
        userMail: String,
        userName: String,
        userPassword:String
    ): Resource<AuthResult>
    suspend fun loginUser(
        userMail: String,
        userPassword:String
    ): Resource<AuthResult>
}