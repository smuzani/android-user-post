package com.muz.userpost.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.muz.userpost.network.Post
import com.muz.userpost.network.User
import com.muz.userpost.network.UserPost

@Entity(tableName = "user_posts")
@TypeConverters(Converters::class)
data class UserPostEntity(
  @PrimaryKey
  val userName: String,
  val user: User,
  val posts: List<Post>
)

class Converters {
  private val gson = Gson()

  @TypeConverter
  fun fromUser(user: User): String = gson.toJson(user)

  @TypeConverter
  fun toUser(json: String): User = gson.fromJson(json, User::class.java)

  @TypeConverter
  fun fromPosts(posts: List<Post>): String = gson.toJson(posts)

  @TypeConverter
  fun toPosts(json: String): List<Post> {
    val type = object : TypeToken<List<Post>>() {}.type
    return gson.fromJson(json, type)
  }
}

fun UserPostEntity.toUserPost() = UserPost(
  user = user,
  posts = posts
)

fun UserPost.toEntity() = UserPostEntity(
  userName = user?.name ?: "",
  user = user ?: User(null, null, null),
  posts = posts ?: emptyList()
) 