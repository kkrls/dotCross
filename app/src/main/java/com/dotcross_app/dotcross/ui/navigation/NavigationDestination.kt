package com.dotcross_app.dotcross.ui.navigation

// Interface implemented by each Class and used by the app's NavGraph
interface NavigationDestination {
    val route: String
    val title: Int
}