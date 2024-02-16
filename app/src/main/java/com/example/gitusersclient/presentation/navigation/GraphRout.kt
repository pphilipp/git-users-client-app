package com.example.gitusersclient.presentation.navigation

import com.example.gitusersclient.presentation.navigation.GraphRoute.ROOT_GRAPH

sealed class Screen(val route: String)
sealed class Graph(val graph: String)

object GraphRoute {
    const val ROOT_GRAPH = "root_graph"
}

data object RootGraph : Graph(graph = ROOT_GRAPH) {
    data object UsersList : Screen(route = "users_list_screen")

    class UserDetails(
        userLogin: String
    ) : Screen(route = "$route_host/$userLogin") {
        companion object {
            const val route_host = "user_details_screen"
            const val argument_key_user_login = "argument_key_user_login"

            const val route_with_args: String =
                "${route_host}/{${argument_key_user_login}}"
        }
    }
}