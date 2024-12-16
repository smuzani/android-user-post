package com.muz.userpost.network

import retrofit2.http.GET

interface UserPostService {
  @GET("SharminSirajudeen/test_resources/users")
  suspend fun getUsers(): List<User>

  @GET("SharminSirajudeen/test_resources/posts")
  suspend fun getPosts(): List<Post>
}
