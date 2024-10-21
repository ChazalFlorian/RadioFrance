package com.fchazal.radiofrance.inject

import com.apollographql.apollo.ApolloClient
import com.fchazal.radiofrance.BuildConfig
import org.koin.dsl.module

val applicationModule = module {
    factory {
        ApolloClient.Builder()
            .serverUrl("openapi.radiofrance.fr/v1/graphql")
            .addHttpHeader("x-token", BuildConfig.API_KEY)
    }
}