package com.base.common.validator.rules

import android.util.Patterns
import com.base.common.validator.ValidationResult
import com.base.common.validator.ValidationRule

class EmailRule(private val errorMessage: String = "Invalid email address") : ValidationRule {
    override fun validate(input: String?): ValidationResult {
        if (input.isNullOrBlank()) return ValidationResult.Valid // Optional, use RequiredRule for mandatory check
        
        return if (Patterns.EMAIL_ADDRESS.matcher(input).matches()) {
            ValidationResult.Valid
        } else {
            ValidationResult.Invalid(errorMessage)
        }
    }
}
