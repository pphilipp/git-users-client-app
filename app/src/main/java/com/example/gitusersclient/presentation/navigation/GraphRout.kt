package com.example.gitusersclient.presentation.navigation

import com.example.gitusersclient.presentation.navigation.GraphRoute.ROOT_GRAPH

sealed class Screen(val route: String)
sealed class Graph(val graph: String)

object GraphRoute {
    const val ROOT_GRAPH = "root_graph"
}

data object RootGraph : Graph(graph = ROOT_GRAPH) {
    data object UsersList : Screen(route = "users_list_screen")

    data object UserDetails : Screen(route = "user_details_screen")

}