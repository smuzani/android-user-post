package com.muz.userpost.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface UserPostRepository {
  fun getUserPosts(): Flow<List<UserPost>>
}

class UserPostRepositoryImpl(
  private val service: UserPostService
) : UserPostRepository {
  override fun getUserPosts(): Flow<List<UserPost>> = flow {
    try {
      // Fetch both users and posts
      val users = service.getUsers()
      val posts = service.getPosts()

      // Group posts by userId and create UserPost objects
      val userPosts = users.map { user ->
        UserPost(
          user = user,
          posts = posts.filter { post ->
            post.userId?.toString() == user.name?.substringAfter("User ")
          }
        )
      }
      emit(userPosts)
    } catch (e: Exception) {
      // Handle error case
      emit(emptyList())
    }
  }
}