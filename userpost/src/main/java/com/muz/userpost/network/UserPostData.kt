package com.muz.userpost.network

data class User(
  val name: String?,
  val thumbnailUrl: String?,
  val url: String?
)

data class Post(
  val userId: Int?,
  val title: String?,
  val body: String?
)

data class UserPost(
  val user: User?,
  val posts: List<Post>?
) {
  val postCount: Int
    get() = posts?.size ?: 0
} 