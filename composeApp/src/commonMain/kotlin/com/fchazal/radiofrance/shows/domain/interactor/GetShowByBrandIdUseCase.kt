package com.fchazal.radiofrance.shows.domain.interactor

import com.fchazal.radiofrance.ShowsQuery
import com.fchazal.radiofrance.shows.domain.model.ShowsResults
import com.fchazal.radiofrance.shows.domain.repository.ShowsRepository

interface GetShowByBrandIdUseCase {
    suspend fun getShows(id: String): ShowsResults
}

class GetShowByBrandIdUseCaseImpl(
    private val repository: ShowsRepository
) : GetShowByBrandIdUseCase {
    override suspend fun getShows(id: String): ShowsResults {
        repository.getShowByStationId(id).let { response ->
            response.data?.let {
                return ShowsResults.Success(
                    it.shows.toShowsResults()
                )
            }
            response.exception?.let {
                return ShowsResults.Error(it.localizedMessage ?: "")
            }
            return ShowsResults.Error("Unknown Error")
        }
    }

    private fun ShowsQuery.Shows?.toShowsResults(): List<ShowsQuery.Node> {
        return this?.edges?.mapNotNull { edge ->
            edge?.node
        } ?: listOf()
    }

}