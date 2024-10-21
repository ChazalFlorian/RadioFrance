package com.fchazal.radiofrance.shows.domain.model

import com.fchazal.radiofrance.ShowsQuery

sealed class ShowsResults{
    data class Success(val shows: List<ShowsQuery.Node>): ShowsResults()
    data class Error(val error: String): ShowsResults()

}