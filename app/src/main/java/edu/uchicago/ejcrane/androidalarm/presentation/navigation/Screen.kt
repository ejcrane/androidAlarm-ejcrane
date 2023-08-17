package edu.uchicago.ejcrane.androidalarm.presentation.navigation

import edu.uchicago.ejcrane.androidalarm.R

sealed class Screen(var route: String, var icon: Int, var title: String) {
    object Alarm : Screen("alarm", R.drawable.baseline_alarm_24, "Alarm")
    object Search : Screen("search", R.drawable.baseline_search_24, "Search")
    object Detail : Screen("detail", 0, "Details")
    object Favorites : Screen("favorites", R.drawable.baseline_favorite_24, "Favorites")
    object Contact : Screen("contact", R.drawable.baseline_contact_support_24, "Contact")
}