package com.base.common.validator

object Validator {
    fun validate(input: String?, rules: List<ValidationRule>): ValidationResult {
        for (rule in rules) {
            val result = rule.validate(input)
            if (result is ValidationResult.Invalid) {
                return result
            }
        }
        return ValidationResult.Valid
    }
}
