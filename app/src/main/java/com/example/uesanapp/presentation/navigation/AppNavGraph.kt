package com.example.uesanapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uesanapp.presentation.auth.LoginScreen
import com.example.uesanapp.presentation.auth.RegisterScreen
import com.example.uesanapp.presentation.favorites.FavoritesScreen
import com.example.uesanapp.presentation.home.HomeScreen
import com.example.uesanapp.presentation.home.HomeViewModel
import com.example.uesanapp.presentation.permissions.GalleryPermissionsScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val homeViewModel: HomeViewModel = viewModel()

    NavHost(navController = navController, startDestination = "register") {
        composable("register") { RegisterScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("home") {
            DrawerScaffold(navController) {
                HomeScreen(viewModel = homeViewModel)
            }
        }
        composable("permissions") {
            DrawerScaffold(navController) {
                GalleryPermissionsScreen()
            }
        }
        composable("favorites") {
            DrawerScaffold(navController) {
                FavoritesScreen(viewModel = homeViewModel)
            }
        }
    }
}