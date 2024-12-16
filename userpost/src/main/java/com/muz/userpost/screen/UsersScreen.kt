package com.muz.userpost.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.muz.spine.Nerve
import com.muz.userpost.network.UserPost

@Composable
fun UsersScreen(
  nerve: Nerve,
  onNavigateToDetails: (UserPost) -> Unit,
  vm: UserViewModel
) {
  val userPostsState by vm.userPosts.collectAsState()

  LaunchedEffect(Unit) {
    nerve.setTitle("Users")
    nerve.setBottomBarButtonText("Refresh")
    nerve.setOnBottomBarButtonClicked { vm.loadUserPosts() }
  }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(nerve.currentScreenPadding)
      .padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    if (userPostsState.isEmpty()) {
      Text("Loading…", style = MaterialTheme.typography.displayLarge)
    } else {
      LazyColumn(
        modifier = Modifier.padding(4.dp)
      ) {
        items(userPostsState, key = { userPost -> userPost.user?.name ?: "" }) { userPost ->
          UserRow(userPost, onNavigateToDetails)
          HorizontalDivider()
        }
      }
    }
  }
}

@Composable
fun UserRow(userPost: UserPost, onNavigateToDetails: (UserPost) -> Unit) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(vertical = 12.dp)
      .clickable {
        onNavigateToDetails(userPost)
      },
    verticalAlignment = Alignment.CenterVertically
  ) {
    Image(
      painter = rememberAsyncImagePainter(userPost.user?.thumbnailUrl),
      contentDescription = "User photo",
      modifier = Modifier
        .size(64.dp)
        .clip(CircleShape)
    )
    Spacer(modifier = Modifier.width(25.dp))
    Column {
      Text(
        text = userPost.user?.name ?: "",
        style = MaterialTheme.typography.headlineLarge
      )
      Text(
        text = "Posts: ${userPost.postCount}",
        style = MaterialTheme.typography.bodyMedium
      )
    }
  }
}

const val USERS = "UsersScreen"