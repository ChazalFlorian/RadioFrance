package com.fchazal.radiofrance.shows.domain.repository

import com.apollographql.apollo.api.ApolloResponse
import com.fchazal.radiofrance.ShowsQuery
import org.koin.core.component.KoinComponent

interface ShowsRepository : KoinComponent {
    suspend fun getShowByStationId(id: String): ApolloResponse<ShowsQuery.Data>
}