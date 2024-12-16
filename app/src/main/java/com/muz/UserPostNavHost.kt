package com.muz

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.muz.userpost.network.UserStore
import com.muz.userpost.screen.USERS
import com.muz.userpost.screen.USER_DETAILS
import com.muz.userpost.screen.UserScreen
import com.muz.userpost.screen.UserViewModel
import com.muz.userpost.screen.UsersScreen
import com.muz.spine.Nerve

// This is the central nerve to navigate between screens
@Composable
fun UserPostNavHost(nerve: Nerve) {
  val navController = rememberNavController()
  NavHost(navController, startDestination = USERS) {
    composable(USERS) {
      val viewModel = hiltViewModel<UserViewModel>()
      UsersScreen(
        nerve,
        onNavigateToDetails = { selectedUser ->
          UserStore.setUser(selectedUser)
          navController.navigate(USER_DETAILS)
        }, viewModel
      )
    }
    composable(USER_DETAILS) {
      UserScreen(nerve)
    }
  }
}