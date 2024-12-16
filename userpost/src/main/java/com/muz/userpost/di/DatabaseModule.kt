package com.muz.userpost.di

import android.content.Context
import androidx.room.Room
import com.muz.userpost.database.UserPostDao
import com.muz.userpost.database.UserPostDatabase
import com.muz.userpost.network.RetrofitBuilder
import com.muz.userpost.network.UserPostRepository
import com.muz.userpost.network.UserPostRepositoryImpl
import com.muz.userpost.network.UserPostService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

  @Provides
  @Singleton
  fun provideDatabase(@ApplicationContext context: Context): UserPostDatabase {
    return Room.databaseBuilder(
      context,
      UserPostDatabase::class.java,
      "user_posts_database"
    ).build()
  }

  @Provides
  @Singleton
  fun provideUserPostDao(database: UserPostDatabase): UserPostDao {
    return database.userPostDao()
  }

  @Provides
  @Singleton
  fun provideRandomUserService(): UserPostService {
    return RetrofitBuilder.userPostService
  }

  @Provides
  @Singleton
  fun provideUserPostRepository(
    service: UserPostService,
    dao: UserPostDao
  ): UserPostRepository {
    return UserPostRepositoryImpl(service, dao)
  }
} 