package edu.uchicago.ejcrane.androidalarm.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import edu.uchicago.ejcrane.androidalarm.presentation.screens.alarm.AlarmScreen
import edu.uchicago.ejcrane.androidalarm.presentation.screens.contact.ContactScreen
import edu.uchicago.ejcrane.androidalarm.presentation.screens.detail.DetailScreen
import edu.uchicago.ejcrane.androidalarm.presentation.screens.favorites.FavoritesScreen
import edu.uchicago.ejcrane.androidalarm.presentation.screens.search.SearchScreen
import edu.uchicago.ejcrane.androidalarm.presentation.viewmodels.AlarmViewModel
import edu.uchicago.ejcrane.androidalarm.presentation.viewmodels.ContactViewModel
import edu.uchicago.ejcrane.androidalarm.presentation.viewmodels.YoutubeViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(
    navController: NavHostController,
    alarmViewModel: AlarmViewModel = viewModel(),
    youtubeViewModel: YoutubeViewModel = viewModel(),
    contactViewModel: ContactViewModel = viewModel()
) {
    AnimatedNavHost(navController, startDestination = Screen.Alarm.route) {
        composable(Screen.Alarm.route) {
            AlarmScreen(alarmViewModel, navController)
        }

        composable(Screen.Search.route) {
            SearchScreen(youtubeViewModel, navController)
        }

        composable(Screen.Detail.route) {
            DetailScreen(youtubeViewModel, navController)
        }

        composable(Screen.Favorites.route) {
            FavoritesScreen(alarmViewModel, navController)
        }

        composable(Screen.Contact.route) {
            ContactScreen(contactViewModel, navController)
        }
    }
}