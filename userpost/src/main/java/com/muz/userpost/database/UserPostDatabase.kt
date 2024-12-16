package com.muz.userpost.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
  entities = [UserPostEntity::class],
  version = 1,
  exportSchema = false
)
@TypeConverters(Converters::class)
abstract class UserPostDatabase : RoomDatabase() {
  abstract fun userPostDao(): UserPostDao
} 