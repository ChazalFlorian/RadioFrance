package com.fchazal.radiofrance.brands.domain.model

import com.fchazal.radiofrance.BrandsQuery.*


sealed class BrandsResults {
    data class Success(val brands: List<Brand>): BrandsResults()
    data class Error(val error: String): BrandsResults()
}