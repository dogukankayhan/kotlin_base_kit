package com.base.common.validator.rules

import com.base.common.validator.ValidationResult
import com.base.common.validator.ValidationRule

class MinLengthRule(
    private val minLength: Int,
    private val errorMessage: String = "Minimum length is $minLength"
) : ValidationRule {
    override fun validate(input: String?): ValidationResult {
        if (input == null) return ValidationResult.Valid
        
        return if (input.length >= minLength) {
            ValidationResult.Valid
        } else {
            ValidationResult.Invalid(errorMessage)
        }
    }
}
