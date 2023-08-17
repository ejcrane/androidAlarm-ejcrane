package edu.uchicago.ejcrane.androidalarm.presentation.screens.search.paging

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import edu.uchicago.ejcrane.androidalarm.presentation.navigation.Screen
import edu.uchicago.ejcrane.androidalarm.presentation.viewmodels.YoutubeViewModel

@Composable
fun VidList(youtubeViewModel: YoutubeViewModel, navController: NavController) {

    //this is what consumes the flow
    val lazyPagingItems = youtubeViewModel.searchState.value.data?.collectAsLazyPagingItems()

    LazyColumn {
        items(
            count = lazyPagingItems!!.itemCount,
            key = lazyPagingItems.itemKey(),
            contentType = lazyPagingItems.itemContentType()
        ) { index ->
            //the following lines define the onItemClick behavior
            val boolItem = lazyPagingItems[index]!!
            VidRow(vid = boolItem) {
                //the following lines define the onItemClick behavior
                youtubeViewModel.setVid(boolItem)
                navController.navigate(
                    route = Screen.Detail.route
                )
            }

        }

        //this will display a spinner in-place of a VidRow in the following events
        lazyPagingItems.apply {
            //fallthrough is not supported
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        Spinner()
                    }
                }
                loadState.append is LoadState.Loading -> {
                    item {
                        Spinner()
                    }
                }
                loadState.prepend is LoadState.Loading -> {
                    item {
                        Spinner()
                    }
                }
            }
        }
    }
}
@Composable
fun Spinner() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .padding(12.dp)
                .align(
                    Alignment.Center
                )
        )
    }
}