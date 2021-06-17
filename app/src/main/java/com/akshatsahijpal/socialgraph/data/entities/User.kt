package com.akshatsahijpal.socialgraph.data.entities

import com.akshatsahijpal.socialgraph.util.Constants
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    var uid:String = "",
    var username: String = "",
    var profilePictureURL: String = Constants.DEFAULT_PROFILE_PICTURE_URL,
    var bio: String = "",
    var followers: List<String> = listOf(),
    @Exclude
    var isFollowing: Boolean = false
    )
