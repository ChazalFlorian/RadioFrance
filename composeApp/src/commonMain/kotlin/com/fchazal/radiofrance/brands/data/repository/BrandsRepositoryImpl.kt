package com.fchazal.radiofrance.brands.data.repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.ApolloResponse
import com.fchazal.radiofrance.BrandsQuery
import com.fchazal.radiofrance.brands.domain.repository.BrandsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BrandsRepositoryImpl : BrandsRepository, KoinComponent {

    private val apolloClient: ApolloClient by inject()

    override suspend fun brands(): ApolloResponse<BrandsQuery.Data> =
        apolloClient.query(BrandsQuery()).execute()
}