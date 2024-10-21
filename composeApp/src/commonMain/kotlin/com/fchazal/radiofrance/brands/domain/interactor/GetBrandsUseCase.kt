package com.fchazal.radiofrance.brands.domain.interactor

import com.fchazal.radiofrance.BrandsQuery
import com.fchazal.radiofrance.brands.domain.model.BrandsResults
import com.fchazal.radiofrance.brands.domain.repository.BrandsRepository

interface GetBrandsUseCase {
    suspend fun getBrands(): BrandsResults
}

class GetBrandsUseCaseImpl(
    private val repository: BrandsRepository,
) : GetBrandsUseCase {
    override suspend fun getBrands(): BrandsResults {
        repository.brands().let { response ->
            response.data?.let {
                return BrandsResults.Success(it.brands.toBrandsResult())
            }
            response.exception?.let { exception ->
                return BrandsResults.Error(exception.localizedMessage ?: "")
            }
            return BrandsResults.Error("unknowm error")
        }
    }

    private fun List<BrandsQuery.Brand?>?.toBrandsResult(): List<BrandsQuery.Brand> {
        return if (!this.isNullOrEmpty()) {
            this.requireNoNulls()
        } else {
            listOf()
        }
    }
}