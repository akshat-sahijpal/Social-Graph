package com.akshatsahijpal.socialgraph.data.entities

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class Post(
    var postUid: String = "",
    var userUid: String = "",
    @Exclude
    var userName: String = "",
    @Exclude
    var profilePictureUrl: String = "",
    var postText: String = "",
    var postImageUrl: String = "",
    var dateL: Long = 0L,
    @Exclude
    var isLiked: Boolean = false,
    @Exclude
    var isLiking: Boolean = false,
    var likedBy: List<String> = listOf()
)