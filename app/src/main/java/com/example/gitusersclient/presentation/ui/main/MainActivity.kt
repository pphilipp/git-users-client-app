package com.example.gitusersclient.presentation.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.gitusersclient.presentation.navigation.RootGraph
import com.example.gitusersclient.presentation.navigation.Screen
import com.example.gitusersclient.presentation.navigation.usersListScreenRoute
import com.example.gitusersclient.presentation.ui.theme.GitUsersClientTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            setContent {
                GitUsersClientTheme {
                    navController = rememberNavController()
                    RootNavigationHost(
                        navigationController = navController,
                        startMainDestination = RootGraph.UsersList
                    )
                }
            }
        }
    }

    @Composable
    fun RootNavigationHost(
        navigationController: NavHostController,
        startMainDestination: Screen
    ) {
        NavHost(
            navController = navigationController,
            route = RootGraph.graph,
            startDestination = startMainDestination.route
        ) {
            usersListScreenRoute(navigationController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GitUsersClientTheme {}
}