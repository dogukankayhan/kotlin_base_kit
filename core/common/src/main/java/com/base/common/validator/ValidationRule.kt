package com.base.common.validator

sealed class ValidationResult {
    object Valid : ValidationResult()
    data class Invalid(val errorMessage: String) : ValidationResult()
}

interface ValidationRule {
    fun validate(input: String?): ValidationResult
}
