package com.fchazal.radiofrance.brands.domain.interactor

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.exception.ApolloHttpException
import com.apollographql.apollo.testing.*
import com.apollographql.mockserver.MockResponse
import com.apollographql.mockserver.MockServer
import com.fchazal.radiofrance.BrandsQuery
import com.fchazal.radiofrance.brands.data.repository.BrandsRepositoryImpl
import com.fchazal.radiofrance.utils.CoroutineTestRule
import io.mockk.MockKAnnotations.init
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.runTest
import okio.ByteString
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetBrandsUseCaseImplTest() {

    @get:Rule
    val coroutineTestRule: CoroutineTestRule = CoroutineTestRule()

    @MockK
    lateinit var repositoryImpl: BrandsRepositoryImpl

    lateinit var getBrandsUseCaseImpl: GetBrandsUseCaseImpl
    lateinit var apolloClient: ApolloClient
    private val error = Throwable()
    private val mockServer = MockServer()

    @Before
    fun setUp() {
        init(this)
        getBrandsUseCaseImpl = GetBrandsUseCaseImpl(repositoryImpl)
        apolloClient = ApolloClient.Builder().networkTransport(QueueTestNetworkTransport()).build()
    }

    @Test
    fun `getBrands - when response is non error and empty - should return error`() = runTest {
        //Given
        val testQuery = BrandsQuery()
        val testData = BrandsQuery.Data(
            brands = listOf()
        )
        apolloClient.enqueueTestResponse(testQuery, testData)

        //When
        val actual = apolloClient.query(testQuery).execute().data!!
        //Then
        assertEquals(0, actual.brands?.count())
    }

    @Test
    fun `getBrands - when response is error - should return error`() = runTest {
        //Given
        apolloClient = ApolloClient.Builder().serverUrl(mockServer.url()).build()
        val testQuery = BrandsQuery()
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
        val testQuery = BrandsQuery()
        val testData = BrandsQuery.Data(
            brands = listOf(
                BrandsQuery.Brand(
                    id = "1",
                    title = "",
                    baseline = null,
                    description = null,
                    liveStream = null
                ),
                BrandsQuery.Brand(
                    id = "2",
                    title = "",
                    baseline = null,
                    description = null,
                    liveStream = null
                )
            )
        )
        apolloClient.enqueueTestResponse(testQuery, testData)

        //When
        val actual = apolloClient.query(testQuery).execute().data!!
        //Then
        assertEquals(2, actual.brands?.count())
    }

    @After
    fun finish() {
        mockServer.close()
    }
}