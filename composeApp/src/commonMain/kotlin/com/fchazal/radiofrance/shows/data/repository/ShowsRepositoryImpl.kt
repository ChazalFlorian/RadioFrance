package com.fchazal.radiofrance.shows.data.repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.ApolloResponse
import com.fchazal.radiofrance.ShowsQuery
import com.fchazal.radiofrance.shows.domain.repository.ShowsRepository
import com.fchazal.radiofrance.type.StationsEnum
import org.koin.core.component.inject

class ShowsRepositoryImpl : ShowsRepository {

    private val apolloClient: ApolloClient by inject()

    override suspend fun getShowByStationId(id: String): ApolloResponse<ShowsQuery.Data> {
        return apolloClient.query(ShowsQuery(StationsEnum.valueOf(id))).execute()
    }

}