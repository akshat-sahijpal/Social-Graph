package com.akshatsahijpal.socialgraph.repository.auth

import android.content.Context
import android.util.Log
import com.akshatsahijpal.socialgraph.data.entities.User
import com.akshatsahijpal.socialgraph.repository.util.safeCall
import com.akshatsahijpal.socialgraph.util.Constants.USER_DB_PATH
import com.akshatsahijpal.socialgraph.util.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepoC @Inject constructor(
    cont: Context
) {
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private var db = FirebaseFirestore.getInstance().collection(USER_DB_PATH)
    suspend fun registerNewUser(
        userMail: String,
        userName: String,
        userPassword: String
    ): Resource<AuthResult> {
        return withContext(Dispatchers.IO) {
            safeCall {
                Log.d("Firebase", "${userMail.trim()}, ${userPassword.trim()}")
                val result = auth.createUserWithEmailAndPassword(userMail.trim(), userPassword.trim()).await()
                val uid = result.user?.uid!!
                val user = User(uid = uid, username = userName)
                db.document(uid).set(user).await()
                Resource.Success(result)
            }
        }
    }

    suspend fun loginUser(userMail: String, userPassword: String): Resource<AuthResult> {
        return withContext(Dispatchers.IO) {
            safeCall {
                val res = auth.signInWithEmailAndPassword(userMail, userPassword).await()
                Resource.Success(res)
            }
        }
    }
}