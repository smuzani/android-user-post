package com.muz.userpost.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muz.userpost.network.RetrofitBuilder
import com.muz.userpost.network.UserPost
import com.muz.userpost.network.UserPostRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor() : ViewModel() {
  private val userPostRepository = UserPostRepositoryImpl(RetrofitBuilder.userPostService)

  init {
    loadUserPosts()
  }

  private val _userPosts = MutableStateFlow<List<UserPost>>(emptyList())
  val userPosts: StateFlow<List<UserPost>> = _userPosts

  fun loadUserPosts() {
    viewModelScope.launch {
      userPostRepository.getUserPosts()
        .flowOn(Dispatchers.IO)
        .catch { e ->
          Log.e("UserViewModel", "Error loading user posts: $e")
          _userPosts.value = emptyList()
        }
        .collect { posts ->
          _userPosts.value = posts
        }
    }
  }
}

