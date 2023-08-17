package edu.uchicago.ejcrane.androidalarm.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import edu.uchicago.ejcrane.androidalarm.common.Constants
import edu.uchicago.ejcrane.androidalarm.data.models.Item
import edu.uchicago.ejcrane.androidalarm.data.repos.ApiProvider
import edu.uchicago.ejcrane.androidalarm.data.repos.YoutubeRepository
import edu.uchicago.ejcrane.androidalarm.presentation.screens.search.paging.Paginate
import edu.uchicago.ejcrane.androidalarm.presentation.screens.search.paging.SearchOperation
import edu.uchicago.ejcrane.androidalarm.presentation.screens.search.paging.SearchState
import edu.uchicago.ejcrane.androidalarm.presentation.screens.search.paging.YoutubeSource
import kotlinx.coroutines.launch

class YoutubeViewModel: ViewModel() {

    private val youtubeRepository: YoutubeRepository = YoutubeRepository(ApiProvider.youtubeApi())

    //MUTABLE STATES
    private var _queryText = mutableStateOf("")
    val queryText: State<String> = _queryText

    private var _vid = mutableStateOf(Constants.fakeVid)
    val vid: State<Item> = _vid

    private val _searchState = mutableStateOf(SearchState())
    val searchState: State<SearchState> = _searchState

    //FUNCTIONS
    fun setVid(vid: Item) {
        _vid.value = vid
    }

    fun setQueryText(query: String) {
        _queryText.value = query
    }

    fun onSearch() {
        _searchState.value = SearchState(searchOperation = SearchOperation.LOADING)
        viewModelScope.launch {
            _searchState.value = SearchState(
                data = Pager(
                    config = PagingConfig(pageSize = 10, prefetchDistance = 5),
                    pagingSourceFactory = {
                        YoutubeSource(
                            youtubeRepository = youtubeRepository,
                            paginateData = Paginate(
                                query = _queryText.value
                            )
                        )
                    }
                ).flow.cachedIn(viewModelScope),
                searchOperation = SearchOperation.DONE
            )
        }
    }
}