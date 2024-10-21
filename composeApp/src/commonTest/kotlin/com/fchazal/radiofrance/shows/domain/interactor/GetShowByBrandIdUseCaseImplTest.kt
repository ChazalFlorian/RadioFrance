package com.fchazal.radiofrance.shows.domain.interactor

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.exception.ApolloHttpException
import com.apollographql.apollo.testing.*
import com.apollographql.mockserver.MockResponse
import com.apollographql.mockserver.MockServer
import com.fchazal.radiofrance.ShowsQuery
import com.fchazal.radiofrance.shows.data.repository.ShowsRepositoryImpl
import com.fchazal.radiofrance.type.StationsEnum
import com.fchazal.radiofrance.utils.CoroutineTestRule
import io.mockk.MockKAnnotations.init
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetShowByBrandIdUseCaseImplTest {

    @get:Rule
    val coroutineTestRule: CoroutineTestRule = CoroutineTestRule()

    @MockK
    lateinit var repository: ShowsRepositoryImpl

    lateinit var useCaseImpl: GetShowByBrandIdUseCaseImpl
    lateinit var apolloClient: ApolloClient
    private val mockServer = MockServer()

    @Before
    fun setUp() {
        init(this)
        useCaseImpl = GetShowByBrandIdUseCaseImpl(repository)
        apolloClient = ApolloClient.Builder().networkTransport(QueueTestNetworkTransport()).build()
    }

    @Test
    fun `getShows - when response is non error and empty - should return error`() = runTest {
        //Given
        val testQuery = ShowsQuery(StationsEnum.FRANCEBLEU)
        val testData = ShowsQuery.Data(
            shows = ShowsQuery.Shows(listOf())
        )

        apolloClient.enqueueTestResponse(testQuery, testData)

        //When
        val actual = apolloClient.query(testQuery).execute().data!!
        //Then
        assertEquals(0, actual.shows?.edges?.count())
    }

    @Test
    fun `getShows - when response is error - should return error`() = runTest {
        //Given
        apolloClient = ApolloClient.Builder().serverUrl(mockServer.url()).build()
        val testQuery = ShowsQuery(StationsEnum.FRANCEBLEU)
        mockServer.enqueue(
            MockResponse.Builder()
                .statusCode(500)
                .body("Internal server error")
                .build()
        )

        //When
        val actual = (apolloClient.query(testQuery).execute().exception as ApolloHttpException)
        //Then
        assertEquals(500, actual.statusCode)
    }

    @Test
    fun `getBrands - when response is non error and non empty - should return success`() = runTest {
        //Given
        val testQuery = ShowsQuery(StationsEnum.FRANCEBLEU)
        val testData = ShowsQuery.Data(
            shows = ShowsQuery.Shows(
                edges = listOf(
                    ShowsQuery.Edge(node = ShowsQuery.Node("title")),
                    ShowsQuery.Edge(node = ShowsQuery.Node("title2"))
                )
            )
        )
        apolloClient.enqueueTestResponse(testQuery, testData)

        //When
        val actual = apolloClient.query(testQuery).execute().data!!
        //Then
        assertEquals(2, actual.shows?.edges?.count())
    }

    @After
    fun finish() {
        mockServer.close()
    }
}