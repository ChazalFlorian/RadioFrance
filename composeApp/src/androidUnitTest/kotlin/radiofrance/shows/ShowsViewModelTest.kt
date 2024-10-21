package radiofrance.shows

import com.fchazal.radiofrance.ShowsQuery
import com.fchazal.radiofrance.shows.ShowsResultState
import com.fchazal.radiofrance.shows.ShowsViewModel
import com.fchazal.radiofrance.shows.domain.interactor.GetShowByBrandIdUseCase
import com.fchazal.radiofrance.shows.domain.model.ShowsResults
import com.fchazal.radiofrance.utils.CoroutineTestRule
import io.mockk.MockKAnnotations.init
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ShowsViewModelTest {

    @get:Rule
    val coroutineTestRule: CoroutineTestRule = CoroutineTestRule()

    @MockK
    private lateinit var useCase: GetShowByBrandIdUseCase
    private lateinit var viewModel: ShowsViewModel

    @Before
    fun setUp() {
        init(this)
        viewModel = ShowsViewModel(useCase)
    }

    @Test
    fun `getShowsById - on Success - should return Success State`() = runTest {
        //Given
        coEvery { useCase.getShows("") } returns ShowsResults.Success(
            listOf()
        )

        //When
        viewModel.getShowsById("")

        //Then
        assertEquals(
            (viewModel.uiState.value as ShowsResultState.Success).shows,
            listOf<ShowsQuery.Node>()
        )
    }

    @Test
    fun `getShowsById - on Failure - should return Error State`() = runTest {
        //Given
        coEvery { useCase.getShows("") } returns ShowsResults.Error("")

        //When
        viewModel.getShowsById("")

        //Then
        assertEquals(
            (viewModel.uiState.value as ShowsResultState.Error).error,
            ""
        )
    }
}