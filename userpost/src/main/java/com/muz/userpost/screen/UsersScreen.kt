package com.muz.userpost.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserRow(userPost: UserPost, onNavigateToDetails: (UserPost) -> Unit) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 8.dp)
      .clickable { onNavigateToDetails(userPost) },
    verticalAlignment = Alignment.CenterVertically
  ) {
    Image(
      painter = rememberAsyncImagePainter(
        model = userPost.user?.thumbnailUrl
      ),
      contentDescription = null,
      modifier = Modifier
        .size(100.dp)
        .clip(CircleShape)
        .semantics { testTagsAsResourceId = true }
        .testTag("thumbnail")
    )

    Column(
      modifier = Modifier
        .weight(1f)
        .padding(start = 16.dp)
    ) {
      Text(
        text = userPost.user?.name ?: "",
        style = MaterialTheme.typography.headlineLarge
      )
      Spacer(modifier = Modifier.height(12.dp))
      Text(
        text = "${userPost.postCount} posts",
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )
    }

    Icon(
      modifier = Modifier
        .semantics { testTagsAsResourceId = true }
        .testTag("caret")
        .height(48.dp),
      imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
      contentDescription = null,
      tint = MaterialTheme.colorScheme.onSurfaceVariant
    )
  }
}

const val USERS = "UsersScreen"