package com.fchazal.radiofrance.brands.domain.repository

import com.apollographql.apollo.api.ApolloResponse
import com.fchazal.radiofrance.BrandsQuery
import org.koin.core.component.KoinComponent

interface BrandsRepository : KoinComponent {
    suspend fun brands(): ApolloResponse<BrandsQuery.Data>
}