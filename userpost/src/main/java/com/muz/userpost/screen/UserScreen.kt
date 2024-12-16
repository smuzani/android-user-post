package com.muz.userpost.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.muz.userpost.network.Post
import com.muz.userpost.network.UserPost

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserScreen(
  userPost: UserPost
) {
  LazyColumn {
    // Header image
    item {
      Image(
        painter = rememberAsyncImagePainter(userPost.user?.url),
        contentDescription = null,
        modifier = Modifier
          .fillMaxWidth()
          .height(240.dp)
          .semantics { testTagsAsResourceId = true }
          .testTag("userImage"),
        contentScale = ContentScale.Crop
      )
      Text(
        text = userPost.user?.name ?: "",
        style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier.padding(16.dp)
      )
    }

    // Posts
    items(userPost.posts ?: emptyList()) { post ->
      PostCard(post)
      Spacer(modifier = Modifier.height(8.dp))
    }
  }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun PostCard(post: Post) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp)
      .semantics { testTagsAsResourceId = true }
      .testTag("postCard"),
    shape = RoundedCornerShape(8.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    ) {
      Text(
        text = post.title ?: "",
        style = MaterialTheme.typography.titleLarge
      )
      Spacer(modifier = Modifier.height(8.dp))
      Text(
        text = post.body ?: "",
        style = MaterialTheme.typography.bodyMedium
      )
    }
  }
}

const val USER_DETAILS = "UserScreen"


