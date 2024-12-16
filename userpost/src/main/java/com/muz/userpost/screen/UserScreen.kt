package com.muz.userpost.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.muz.spine.Nerve
import com.muz.userpost.network.UserPost

@Composable
fun UserScreen(
  nerve: Nerve,
  userPost: UserPost
) {
  LaunchedEffect(Unit) {
    nerve.setTitle(userPost.user?.name ?: "User Profile")
    nerve.setBottomBarButtonText("")
    nerve.setOnBottomBarButtonClicked { }
  }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(nerve.currentScreenPadding)
      .padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      modifier = Modifier.fillMaxWidth(),
      text = "User Profile",
      style = MaterialTheme.typography.displayMedium,
      textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(16.dp))
    HorizontalDivider()
    Spacer(modifier = Modifier.height(24.dp))

    Image(
      painter = rememberAsyncImagePainter(userPost.user?.url),
      contentDescription = "User photo",
      modifier = Modifier
        .size(200.dp)
        .clip(CircleShape)
    )
    Spacer(modifier = Modifier.height(24.dp))

    Text(
      modifier = Modifier.fillMaxWidth(),
      text = userPost.user?.name ?: "",
      style = MaterialTheme.typography.headlineLarge
    )

    Spacer(modifier = Modifier.height(16.dp))
    HorizontalDivider()
    Spacer(modifier = Modifier.height(16.dp))

    Text(
      text = "Posts (${userPost.postCount})",
      style = MaterialTheme.typography.headlineMedium
    )

    LazyColumn {
      items(userPost.posts ?: emptyList()) { post ->
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
        ) {
          Text(
            text = post.title ?: "",
            style = MaterialTheme.typography.titleLarge
          )
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = post.body ?: "",
            style = MaterialTheme.typography.bodyMedium
          )
          HorizontalDivider(modifier = Modifier.padding(top = 8.dp))
        }
      }
    }
  }
}

const val USER_DETAILS = "UserScreen"


