package com.muz.userpost.network

import com.muz.userpost.database.UserPostDao
import com.muz.userpost.database.toEntity
import com.muz.userpost.database.toUserPost
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface UserPostRepository {
  fun getUserPosts(): Flow<List<UserPost>>
  suspend fun refreshUserPosts()
}

class UserPostRepositoryImpl(
  private val service: UserPostService,
  private val dao: UserPostDao
) : UserPostRepository {
  override fun getUserPosts(): Flow<List<UserPost>> =
    dao.getAllUserPosts().map { entities ->
      entities.map { it.toUserPost() }
    }

  override suspend fun refreshUserPosts() {
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

      // Save to database
      dao.clearUserPosts()
      dao.insertUserPosts(userPosts.map { it.toEntity() })
    } catch (e: Exception) {
      // Handle error case
      throw e
    }
  }
}