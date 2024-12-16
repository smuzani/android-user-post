package com.muz.userpost.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
  val name: String?,
  val thumbnailUrl: String?,
  val url: String?
) : Parcelable

@Parcelize
data class Post(
  val userId: Int?,
  val title: String?,
  val body: String?
) : Parcelable

@Parcelize
data class UserPost(
  val user: User?,
  val posts: List<Post>?
) : Parcelable {
  val postCount: Int
    get() = posts?.size ?: 0
} 