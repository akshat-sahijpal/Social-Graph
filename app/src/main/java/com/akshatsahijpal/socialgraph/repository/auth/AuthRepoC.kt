package com.akshatsahijpal.socialgraph.repository.auth

import com.akshatsahijpal.socialgraph.util.Resource
import com.google.firebase.auth.AuthResult

class AuthRepoC: AuthRepository {
    override suspend fun registerNewUser(
        userMail: String,
        userName: String,
        userPassword: String
    ): Resource<AuthResult> {
        TODO("Not yet implemented")
    }

    override suspend fun loginUser(userMail: String, userPassword: String): Resource<AuthResult> {
        TODO("Not yet implemented")
    }
}