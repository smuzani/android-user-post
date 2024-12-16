package com.muz.userpost.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserPostDao {
  @Query("SELECT * FROM user_posts")
  fun getAllUserPosts(): Flow<List<UserPostEntity>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertUserPosts(posts: List<UserPostEntity>)

  @Query("DELETE FROM user_posts")
  suspend fun clearUserPosts()
} 