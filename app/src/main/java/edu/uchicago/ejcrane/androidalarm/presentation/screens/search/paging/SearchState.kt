package edu.uchicago.ejcrane.androidalarm.presentation.screens.search.paging

import androidx.paging.PagingData
import edu.uchicago.ejcrane.androidalarm.data.models.Item
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchOperation: SearchOperation = SearchOperation.INITIAL,
    val data: Flow<PagingData<Item>>? = null
)

enum class SearchOperation { LOADING, INITIAL, DONE }
