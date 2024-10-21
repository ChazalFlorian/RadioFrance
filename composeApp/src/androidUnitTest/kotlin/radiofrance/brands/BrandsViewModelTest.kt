package radiofrance.brands

import com.fchazal.radiofrance.BrandsQuery
import com.fchazal.radiofrance.brands.BrandsResultState
import com.fchazal.radiofrance.brands.BrandsViewModel
import com.fchazal.radiofrance.brands.domain.interactor.GetBrandsUseCase
import com.fchazal.radiofrance.brands.domain.model.BrandsResults
import com.fchazal.radiofrance.brands.presentation.model.BrandsUI
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
class BrandsViewModelTest {

    @get:Rule
    val coroutineTestRule: CoroutineTestRule = CoroutineTestRule()

    @MockK
    private lateinit var useCase: GetBrandsUseCase
    private lateinit var viewModel: BrandsViewModel

    @Before
    fun setUp() {
        init(this)
        viewModel = BrandsViewModel(useCase)
    }

    @Test
    fun `getBrands - on Success - should return Success State`() = runTest {
        //Given
        coEvery { useCase.getBrands() } returns BrandsResults.Success(
            listOf(
                BrandsQuery.Brand(
                    "id",
                    "title",
                    null,
                    null,
                    null
                )
            )
        )

        //When
        viewModel.getBrands()

        //Then
        assertEquals(
            (viewModel.uiState.value as BrandsResultState.Success).brands,
            listOf(
                BrandsUI(
                    id = "id",
                    title = "title",
                    description = "",
                    baseline = "",
                )
            )
        )
    }

    @Test
    fun `getBrands - on Failure - should return Error State`() = runTest {
        //Given
        coEvery { useCase.getBrands() } returns BrandsResults.Error("")

        //When
        viewModel.getBrands()

        //Then
        assertEquals(
            (viewModel.uiState.value as BrandsResultState.Error).error,
            ""
        )
    }
}