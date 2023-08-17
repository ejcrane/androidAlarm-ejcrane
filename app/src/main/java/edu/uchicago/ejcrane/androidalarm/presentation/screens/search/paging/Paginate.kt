package edu.uchicago.ejcrane.androidalarm.presentation.screens.search.paging

data class Paginate(
    val query: String = "",
    val maxResult: Int = 10,
    val pageToken: String? = null
)
