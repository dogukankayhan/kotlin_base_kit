package com.base.common.validator.rules

import com.base.common.validator.ValidationResult
import com.base.common.validator.ValidationRule

class RequiredRule(private val errorMessage: String = "This field is required") : ValidationRule {
    override fun validate(input: String?): ValidationResult {
        return if (input.isNullOrBlank()) {
            ValidationResult.Invalid(errorMessage)
        } else {
            ValidationResult.Valid
        }
    }
}
