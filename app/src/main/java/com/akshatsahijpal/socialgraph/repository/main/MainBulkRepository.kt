package com.akshatsahijpal.socialgraph.repository.main

import android.net.Uri
import com.akshatsahijpal.socialgraph.data.entities.Post
import com.akshatsahijpal.socialgraph.repository.util.safeCall
import com.akshatsahijpal.socialgraph.util.Constants
import com.akshatsahijpal.socialgraph.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class MainBulkRepository @Inject constructor(){
    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()
    private val storage = FirebaseStorage.getInstance()
    private val userCollection = firestore.collection(Constants.USER_DB_PATH)
    private val postCollection = firestore.collection(Constants.USER_POST_PATH)
    private val commentCollection = firestore.collection(Constants.USER_COMMENTS_PATH)
    suspend fun createPost(imageURI: Uri, postText: String) = withContext(Dispatchers.IO) {
        safeCall {
            val userUid = auth.uid!!
            val postUid = UUID.randomUUID().toString()
            val imagePostTaskResult =
                storage.getReference(postUid).putFile(imageURI).await() // uploads an image to db
            val imageURL = imagePostTaskResult.metadata?.reference?.downloadUrl?.await().toString()
            val post = Post(
                postUid = postUid,
                userUid = userUid,
                postText = postText,
                postImageUrl = imageURL,
                dateL = System.currentTimeMillis()
            )
            postCollection.document(postUid).set(post).await()
            Resource.Success(Any())
        }
    }
}