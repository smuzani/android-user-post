package com.muz

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.muz.userpost.network.UserPost
import com.muz.userpost.screen.USERS
import com.muz.userpost.screen.USER_DETAILS
import com.muz.userpost.screen.UserScreen
import com.muz.userpost.screen.UserViewModel
import com.muz.userpost.screen.UsersScreen

@Composable
fun UserPostNavHost() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = USERS) {
        composable(USERS) {
            val viewModel = hiltViewModel<UserViewModel>()
            UsersScreen(
                onNavigateToDetails = { userPost ->
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        key = "userPost",
                        value = userPost
                    )
                    navController.navigate(USER_DETAILS)
                },
                vm = viewModel
            )
        }
        composable(USER_DETAILS) {
            val userPost = navController.previousBackStackEntry?.savedStateHandle?.get<UserPost>("userPost")
            userPost?.let { post ->
                UserScreen(userPost = post)
            }
        }
    }
}